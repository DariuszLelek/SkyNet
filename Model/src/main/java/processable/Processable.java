/*
 * Created by Dariusz Lelek on 9/10/17 9:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

package processable;

import priority.Priority;

public abstract class Processable {

  protected Priority priority;

  // TODO check if needed
  protected int retryTimeoutMS = Integer.MIN_VALUE;  // Run once

  public abstract boolean execute();

  public abstract boolean canProcess();

  public abstract int getPriority();

  public boolean isNotEmpty(){
    return true;
  }

}
