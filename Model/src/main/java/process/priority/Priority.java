/*
 * Created by Dariusz Lelek on 9/12/17 11:50 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process.priority;

public enum Priority {
  NONE(0),
  LOW(1),
  MEDIUM(2),
  HIGH(3);

  private final int value;

  Priority(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
