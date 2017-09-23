/*
 * Created by Dariusz Lelek on 9/23/17 10:11 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process.control;

public interface StateControl {
  void start();
  void stop();
  boolean isRunning();
}
