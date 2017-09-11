/*
 * Created by Dariusz Lelek on 9/11/17 10:47 PM
 * Copyright (c) 2017. All rights reserved.
 */

package processing.message;

public enum MessagePriority {
  NONE(0),
  LOW(1),
  MEDIUM(2),
  HIGH(3);

  private final int value;

  MessagePriority(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
