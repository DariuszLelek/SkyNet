/*
 * Created by Dariusz Lelek on 9/21/17 7:58 PM
 * Copyright (c) 2017. All rights reserved.
 */

package worker;

import org.joda.time.DateTime;

/**
 * Background task
 */
public abstract class Worker implements Runnable{
  private final long delay;
  //private final WorkerType type;

  boolean working = true;

  public Worker(long delay) {
    this.delay = delay;
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
    return delay;
  }

  @Override
  public abstract void run();
}
