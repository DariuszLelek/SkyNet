/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */

package execute;

import org.apache.log4j.Logger;
import process.control.StateControl;
import process.processable.EmptyProcessable;
import process.processable.Processable;

import java.util.ArrayList;
import java.util.List;

public class ProcessableExecutor implements StateControl {
  private final Logger logger = Logger.getLogger(ProcessableExecutor.class);

  private final EmptyProcessable emptyProcessable = EmptyProcessable.INSTANCE;

  private final List<Processable> pendingProcessables = new ArrayList<>();
  private final List<ProcessableRunner> ACTIVE_RUNNERS = new ArrayList<>();

  private boolean running = false;

  private final int PENDING_CHECK_DELAY = 1 * 1000;
  private final int PROCESS_NEXT_DELAY = 100;
  private final int LOG_IDLE_FREQUENCY = 10;

  private int logIdleCounter = LOG_IDLE_FREQUENCY;

  public synchronized void addProcessable(Processable processable) {
    if(!pendingProcessables.contains(processable)){
      pendingProcessables.add(processable);
    }
  }

  @Override
  public void start() {
    setRunning(true);

    new Thread(() -> {
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
            if (logIdleCounter++ >= LOG_IDLE_FREQUENCY) {
              logIdleCounter = 0;
              logger.info("Idle... - No pending processables");
            }
            try {
              Thread.sleep(PENDING_CHECK_DELAY);
            } catch (InterruptedException e) {
              logger.error("startExecutor()", e);
            }
          }
        }
      }
    }).start();
  }

  @Override
  public void stop() {
    setRunning(false);
    stopActiveRunners();
  }

  public boolean isRunning() {
    return running;
  }

  private synchronized void setRunning(boolean newValue){
    running = newValue;
  }

  private synchronized void startProcessableRunner(final Processable processable) {
    if(processable.canProcess()){
      removeFromPending(processable);

      ProcessableRunner runner = new ProcessableRunner(processable);

      Thread thread = new Thread(runner);
      thread.start();

      addRunner(runner);
    }
  }

  private synchronized void addRunner(ProcessableRunner runner){
    ACTIVE_RUNNERS.add(runner);
  }

  private synchronized void stopActiveRunners(){
    ACTIVE_RUNNERS.stream().filter(ProcessableRunner::isRunning).forEach(ProcessableRunner::stop);
  }

  private synchronized Processable getHighestPriorityProcessable() {
    Processable highestPriorityProcessable = emptyProcessable;

    for (Processable processable : pendingProcessables) {
      if (processable.getPriority() > highestPriorityProcessable.getPriority()) {
        highestPriorityProcessable = processable;
      }
    }

    return highestPriorityProcessable;
  }

  private synchronized void removeFromPending(Processable processable) {
    if (pendingProcessables.contains(processable)) {
      pendingProcessables.remove(processable);
    }
  }
}
