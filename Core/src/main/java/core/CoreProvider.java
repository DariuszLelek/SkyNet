/*
 * Created by Dariusz Lelek on 9/23/17 10:13 PM
 * Copyright (c) 2017. All rights reserved.
 */

package core;

public class CoreProvider {
  private static final Core core = new Core();

  public static Core getCore() {
    return core;
  }
}
