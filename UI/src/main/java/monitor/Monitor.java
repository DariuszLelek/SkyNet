/*
 * Created by Dariusz Lelek on 9/22/17 6:42 PM
 * Copyright (c) 2017. All rights reserved.
 */

package monitor;

import javafx.application.Platform;
import process.control.StateControl;

public abstract class Monitor implements StateControl{
  private boolean running = true;
  private final Thread monitorThread = new Thread(getMonitorRunnable());
  private final long monitorDelay;

  public Monitor(long monitorDelay) {
    this.monitorDelay = monitorDelay;
  }

  @Override
  public void start(){
    monitorThread.start();
  }

  @Override
  public void stop(){
      this.running = false;
  }

  private Runnable getMonitorRunnable(){
    return () -> {
      while (running){
        Platform.runLater(this::update);
        try {
          Thread.sleep(monitorDelay);
        } catch (InterruptedException e) {
          // TODO
          e.printStackTrace();
        }
      }
    };
  }

  @Override
  public boolean isRunning() {
    return running;
  }

  abstract void update();

}
