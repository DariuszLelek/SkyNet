/*
 * Created by Dariusz Lelek on 9/12/17 7:38 PM
 * Copyright (c) 2017. All rights reserved.
 */

package execute;

import org.apache.log4j.Logger;
import process.processable.Processable;

public class ProcessableRunner implements Runnable {

  private final static Logger logger = Logger.getLogger(ProcessableRunner.class);

  private final static int RETRY_DELAY = 1 * 1000;
  private final static int MAX_RETRY_TIMES = 10;

  private final Processable processable;

  private boolean running = true;
  private int retryTimes = 0;

  public ProcessableRunner(Processable processable) {
    this.processable = processable;
  }

  @Override
  public void run() {
    while (isRunning()) {
      if(!processable.isActive()){
        running = false;
        return;
      }

      if (processable.process()) {
        stop();
      } else {
        retryTimes++;
      }

      checkMaxRetryCounter();
      sleep();
    }
  }

  private void checkMaxRetryCounter() {
    if (retryTimes >= MAX_RETRY_TIMES) {
      logger.error("checkMaxRetryCounter() - FAIL - Max retry limit - " + processable.toString());
      stop();
    }
  }

  private void sleep() {
    if (isRunning()) {
      try {
        Thread.sleep(RETRY_DELAY);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public synchronized void stop() {
    this.running = false;
  }

  public synchronized boolean isRunning() {
    return running;
  }
}
