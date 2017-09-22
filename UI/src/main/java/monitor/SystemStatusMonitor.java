/*
 * Created by Dariusz Lelek on 9/22/17 6:42 PM
 * Copyright (c) 2017. All rights reserved.
 */

package monitor;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;

public class SystemStatusMonitor extends Monitor {
  private final ObservableBooleanValue hibernateSessionFactory = new SimpleBooleanProperty(false);



  static{
    // startMonitor();
  }

  @Override
  void startMonitor() {

  }
}
