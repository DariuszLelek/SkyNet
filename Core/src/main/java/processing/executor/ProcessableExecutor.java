/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */

package processing.executor;

import org.apache.log4j.Logger;
import processable.Processable;

import java.util.ArrayList;
import java.util.List;

public class ProcessableExecutor extends Executor {
  private final static Logger logger = Logger.getLogger(ProcessableExecutor.class);

  private final static List<Processable> pendingProcessables = new ArrayList<>();
  private final static List<Processable> runningProcessables = new ArrayList<>();

  private static boolean running = true;

  private static final int PENDING_CHECK_DELAY = 1 * 1000;
  private static final int PROCESS_NEXT_DELAY = 100;

  static {
    startExecutorThread();
  }

  public synchronized static void addProcessable(Processable processable) {
    pendingProcessables.add(processable);
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
    logger.info("executeProcessable()" + processable.toString());

    removeFromPending(processable);
    processable.execute();
  }

  private synchronized static Processable getHighestPriorityProcessable() {
    Processable highestPriorityProcessable = Processable.EMPTY;

    for (Processable processable : pendingProcessables) {
      if (processable.getPriorityValue() > highestPriorityProcessable.getPriorityValue() &&
          processable.canBeProcessed()) {
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
