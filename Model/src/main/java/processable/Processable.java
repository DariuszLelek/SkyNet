/*
 * Created by Dariusz Lelek on 9/10/17 9:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

package processable;

public abstract class Processable {
  public static final Processable EMPTY = new Processable() {
    @Override
    public void execute() {

    }

    @Override
    public boolean canBeProcessed() {
      return false;
    }

    @Override
    public int getPriorityValue() {
      return Integer.MIN_VALUE;
    }

    @Override
    public String toString() {
      return "EmptyProcessable";
    }
  };

  public abstract void execute();

  public abstract boolean canBeProcessed();

  public abstract int getPriorityValue();

}
