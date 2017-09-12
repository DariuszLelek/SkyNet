/*
 * Created by Dariusz Lelek on 9/12/17 6:51 PM
 * Copyright (c) 2017. All rights reserved.
 */

package priority;

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
}
