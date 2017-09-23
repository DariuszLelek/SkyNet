/*
 * Created by Dariusz Lelek on 9/22/17 6:42 PM
 * Copyright (c) 2017. All rights reserved.
 */

package monitor;

import javafx.application.Platform;

public abstract class Monitor {
  private boolean running = true;
  private final Thread monitorThread = new Thread(getMonitorRunnable());
  private final long monitorDelay;

  public Monitor(long monitorDelay) {
    this.monitorDelay = monitorDelay;
  }

  void start(){
    monitorThread.start();
  }

  void stop(){
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

  abstract void update();

}
