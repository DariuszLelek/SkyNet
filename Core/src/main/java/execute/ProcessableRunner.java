/*
 * Created by Dariusz Lelek on 9/12/17 7:38 PM
 * Copyright (c) 2017. All rights reserved.
 */

package execute;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import processable.Processable;

public class ProcessableRunner implements Runnable {

  private final static Logger logger = Logger.getLogger(ProcessableRunner.class);

  private final static int RETRY_DELAY = 1 * 1000;
  private final static int MAX_RETRY_TIMES = 10;

  private final Processable processable;
  private final boolean repeatable;

  private DateTime lastProcessed = DateTime.now();

  private boolean running = true;
  private int retryTimes = 0;

  public ProcessableRunner(Processable processable) {
    this.processable = processable;
    this.repeatable = processable.getRepeatDelayMS() > 0;
  }

  @Override
  public void run() {
    while (isRunning()) {
      if (repeatable) {
        handleAsRepeatable();
      } else {
        handleAsNotRepeatable();
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

  private void handleAsNotRepeatable() {
    if (processable.process()) {
      logger.debug("handleAsNotRepeatable() - SUCCESS - " + processable.toString());
      stop();
    } else {
      retryTimes++;
    }
  }

  private void handleAsRepeatable() {
    if (isTimeToRepeat()) {
      if (processable.process()) {
        retryTimes = 0;
        lastProcessed = DateTime.now();
        logger.debug("handleAsRepeatable() - SUCCESS - " + processable.toString());
      } else {
        retryTimes++;
      }
    }
  }

  private boolean isTimeToRepeat() {
    return DateTime.now().isAfter(lastProcessed.plus(processable.getRepeatDelayMS()));
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
