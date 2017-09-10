/*
 * Created by Dariusz Lelek on 9/10/17 9:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

package processable;

public class EmptyProcessable extends Processable {

  public void execute() {
  }

  public boolean canBeProcessed() {
    return false;
  }

  public int getPriorityValue() {
    return Integer.MIN_VALUE;
  }

  @Override
  public String toString() {
    return EmptyProcessable.class.getName();
  }
}
