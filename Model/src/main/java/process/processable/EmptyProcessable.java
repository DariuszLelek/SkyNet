/*
 * Created by Dariusz Lelek on 9/12/17 11:50 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process.processable;

public class EmptyProcessable extends Processable{
  public static final EmptyProcessable INSTANCE = new EmptyProcessable();

  @Override
  public boolean canProcess() {
    return false;
  }

  @Override
  public boolean process() {
    return true;
  }

  @Override
  public int getPriority() {
    return Integer.MIN_VALUE;
  }
}
