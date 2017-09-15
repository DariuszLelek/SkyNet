/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */

package execute;

import org.apache.log4j.Logger;
import process.processable.EmptyProcessable;
import process.processable.Processable;

import java.util.ArrayList;
import java.util.List;

public class ProcessableExecutor {
  private final static Logger logger = Logger.getLogger(ProcessableExecutor.class);

  private final static EmptyProcessable emptyProcessable = EmptyProcessable.INSTANCE;

  private final static List<Processable> pendingProcessables = new ArrayList<>();
  private final static List<ProcessableRunner> ACTIVE_RUNNERS = new ArrayList<>();

  private static boolean running = true;

  private static final int PENDING_CHECK_DELAY = 1 * 1000;
  private static final int PROCESS_NEXT_DELAY = 100;
  private static final int LOG_IDLE_FREQUENCY = 10;

  private static int logIdleCounter = LOG_IDLE_FREQUENCY;

  static {
    startExecutor();
  }

  public synchronized static void addProcessable(Processable processable) {
    if(!pendingProcessables.contains(processable)){
      pendingProcessables.add(processable);
    }
  }

  public static void stopExecutor() {
    running = false;
    stopActiveRunners();
  }

  private static void startExecutor() {
    Thread thread = new Thread(() -> {
      while (running) {
        synchronized (pendingProcessables) {
          if (!pendingProcessables.isEmpty()) {
            startProcessableRunner(getHighestPriorityProcessable());
            try {
              Thread.sleep(PROCESS_NEXT_DELAY);
            } catch (InterruptedException e) {
              logger.error("startExecutor()", e);
            }
          } else {
            if(logIdleCounter++ >= LOG_IDLE_FREQUENCY){
              logIdleCounter = 0;
              logger.info("Running... - No pending processables");
            }
            try {
              Thread.sleep(PENDING_CHECK_DELAY);
            } catch (InterruptedException e) {
              logger.error("startExecutor()", e);
            }
          }
        }
      }
    });

    thread.start();
  }

  private synchronized static void startProcessableRunner(final Processable processable) {
    if(processable.hasInstructions()){
      removeFromPending(processable);

      ProcessableRunner runner = new ProcessableRunner(processable);

      Thread thread = new Thread(runner);
      thread.start();

      addRunner(runner);
    }
  }

  private synchronized static void addRunner(ProcessableRunner runner){
    ACTIVE_RUNNERS.add(runner);
  }

  private synchronized static void stopActiveRunners(){
    ACTIVE_RUNNERS.stream().filter(ProcessableRunner::isRunning).forEach(ProcessableRunner::stop);
  }

  private synchronized static Processable getHighestPriorityProcessable() {
    Processable highestPriorityProcessable = emptyProcessable;

    for (Processable processable : pendingProcessables) {
      if (processable.getPriority() > highestPriorityProcessable.getPriority() &&
          processable.hasInstructions()) {
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
