/*
 * Created by Dariusz Lelek on 9/12/17 11:50 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process.processable;

import process.priority.Priority;

public abstract class Processable {

  protected Priority priority;

  public abstract boolean process();

  public abstract int getPriority();

  public abstract boolean hasInstructions();

}
