/*
 * Created by Dariusz Lelek on 9/12/17 7:38 PM
 * Copyright (c) 2017. All rights reserved.
 */

package execute;

import org.apache.log4j.Logger;
import processable.Processable;

public class ProcessableRunner implements Runnable {

  private final static Logger logger = Logger.getLogger(ProcessableRunner.class);

  private final static int RETRY_DELAY = 1 * 1000;
  private final static int MAX_RETRY_TIMES = 5;

  private final Processable processable;

  private boolean running = true;
  private int retryTimes = 0;

  public ProcessableRunner(Processable processable) {
    this.processable = processable;
  }

  @Override
  public void run() {
    while (running){
      if(processable.execute()){
        stop();
      }else{
        if(++ retryTimes >= MAX_RETRY_TIMES){
          logFail();
          stop();
          break;
        }
        sleep();
      }
    }
  }

  private void logFail(){
    logger.warn("run() - failed to execute: " + processable.toString()
        + " reached Maximum Retry Counter:" + MAX_RETRY_TIMES);
  }

  private void sleep(){
    try {
      Thread.sleep(RETRY_DELAY);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void stop(){
    this.running = false;
  }

  public boolean isRunning(){
    return running;
  }
}
