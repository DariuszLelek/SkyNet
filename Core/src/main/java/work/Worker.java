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

  protected boolean running = false;

  public Worker(WorkerConfig config) {
    this.config = config;
  }

  public void start(){
    this.running = true;
  }

  public void stop(){
    this.running = false;
  }

  public long getDelay() {
    return config.getDelay();
  }

  @Override
  public void run(){
    if(running){
      runWorker();
    }
  }

  @Override
  public boolean isRunning() {
    return running;
  }

  public abstract void runWorker();
}
