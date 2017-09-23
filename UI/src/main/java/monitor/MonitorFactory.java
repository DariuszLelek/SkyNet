/*
 * Created by Dariusz Lelek on 9/23/17 8:38 PM
 * Copyright (c) 2017. All rights reserved.
 */

package monitor;

import java.util.ArrayList;
import java.util.List;

public class MonitorFactory {

  private static final SystemStatusMonitor systemStatusMonitor = new SystemStatusMonitor();
  private static final List<Monitor> monitors = new ArrayList<>();

  static {
    monitors.add(systemStatusMonitor);
  }

  public static SystemStatusMonitor getSystemStatusMonitor() {
    return systemStatusMonitor;
  }

  public static void close(){
    monitors.forEach(Monitor::stop);
  }
}
