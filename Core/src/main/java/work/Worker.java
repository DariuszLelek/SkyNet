/*
 * Created by Dariusz Lelek on 9/21/17 7:58 PM
 * Copyright (c) 2017. All rights reserved.
 */

package work;

import process.control.StateControl;

/**
 * Background task
 */
public abstract class Worker implements Runnable, StateControl{
  private final WorkerConfig config;

  protected boolean working = true;

  public Worker(WorkerConfig config) {
    this.config = config;
  }

  public void start(){
    this.working = true;
  }

  public void stop(){
    this.working = false;
  }

  public boolean isWorking() {
    return working;
  }

  public long getDelay() {
    return config.getDelay();
  }

  @Override
  public void run(){
    if(working){
      runWorker();
    }
  }

  @Override
  public boolean isRunning() {
    return working;
  }

  public abstract void runWorker();
}
