/*
 * Created by Dariusz Lelek on 9/11/17 10:47 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process.message;

import process.priority.Priority;

public enum MessageType {
  EMPTY(Priority.NONE),

  UI(Priority.HIGH),
  VOICE(Priority.HIGH),
  SYSTEM(Priority.HIGH);

  private final Priority priority;

  MessageType(Priority priority) {
    this.priority = priority;
  }

  public Priority getPriority() {
    return priority;
  }

  @Override
  public String toString() {
    return "MessageType{" +
        "priority=" + priority +
        '}';
  }
}
