/*
 * Created by Dariusz Lelek on 9/12/17 7:00 PM
 * Copyright (c) 2017. All rights reserved.
 */

package processable;

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
