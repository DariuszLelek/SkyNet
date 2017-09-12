/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */

package execute;

import org.apache.log4j.Logger;
import processable.EmptyProcessable;
import processable.Processable;

import java.util.ArrayList;
import java.util.List;

public class ProcessableExecutor extends Executor {
  private final static Logger logger = Logger.getLogger(ProcessableExecutor.class);

  private final static List<Processable> pendingProcessables = new ArrayList<>();
  private final static List<Processable> runningProcessables = new ArrayList<>();

  private final static EmptyProcessable emptyProcessable = EmptyProcessable.INSTANCE;

  private static boolean running = true;

  private static final int PENDING_CHECK_DELAY = 1 * 1000;
  private static final int PROCESS_NEXT_DELAY = 100;

  static {
    startExecutorThread();
  }

  public synchronized static void addProcessable(Processable processable) {
    if(!pendingProcessables.contains(processable)){
      pendingProcessables.add(processable);
    }
  }

  public static void stopExecutorThread() {
    running = false;
  }

  private static void startExecutorThread() {
    Thread thread = new Thread(() -> {
      while (running) {
        synchronized (pendingProcessables) {
          if (!pendingProcessables.isEmpty()) {
            executeProcessable(getHighestPriorityProcessable());
            try {
              Thread.sleep(PROCESS_NEXT_DELAY);
            } catch (InterruptedException e) {
              logger.error("startExecutorThread()", e);
            }
          } else {
            // TODO change to heart beat
            logger.info("No pending processables, sleeping " + PENDING_CHECK_DELAY);
            try {
              Thread.sleep(PENDING_CHECK_DELAY);
            } catch (InterruptedException e) {
              logger.error("startExecutorThread()", e);
            }
          }
        }
      }
    });

    thread.start();
  }

  private static void executeProcessable(final Processable processable) {
    if(processable.isNotEmpty()){
      removeFromPending(processable);
      logger.info("executeProcessable() - " + processable.toString());

      // TODO execute in new thread
      processable.execute();
    }
  }

  private synchronized static Processable getHighestPriorityProcessable() {
    Processable highestPriorityProcessable = emptyProcessable;

    for (Processable processable : pendingProcessables) {
      if (processable.getPriority() > highestPriorityProcessable.getPriority() &&
          processable.canProcess()) {
        highestPriorityProcessable = processable;
      }
    }

    return highestPriorityProcessable;
  }

  private synchronized static void removeFromPending(Processable processable) {
    if (pendingProcessables.contains(processable)) {
      pendingProcessables.remove(processable);
    }
  }
}
