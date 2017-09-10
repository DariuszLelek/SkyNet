/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */

package processing.message.model;

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
