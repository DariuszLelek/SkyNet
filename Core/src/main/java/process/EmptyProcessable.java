/*
 * Created by Dariusz Lelek on 9/12/17 1:13 AM
 * Copyright (c) 2017. All rights reserved.
 */

package process;

import processable.Processable;

public class EmptyProcessable extends Processable{
  public static final EmptyProcessable INSTANCE = new EmptyProcessable();

  @Override
  public void execute() {

  }

  @Override
  public boolean canProcess() {
    return false;
  }

  @Override
  public int getPriority() {
    return Integer.MIN_VALUE;
  }

  @Override
  public boolean isNotEmpty() {
    return false;
  }
}
