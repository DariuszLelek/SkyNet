/*
 * Created by Dariusz Lelek on 9/11/17 10:47 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process.message;

import priority.Priority;

public enum MessageType {
  EMPTY("Empty", Priority.NONE),

  VOICE("Voice", Priority.HIGH),
  SYSTEM("System", Priority.HIGH);

  private final String type;
  private final Priority priority;

  MessageType(String type, Priority priority) {
    this.type = type;
    this.priority = priority;
  }

  public String getType() {
    return type;
  }

  public Priority getPriority() {
    return priority;
  }

  @Override
  public String toString() {
    return "{" +
        "type='" + type + '\'' +
        ", priority=" + priority.getValue() +
        '}';
  }
}
