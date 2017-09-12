/*
 * Created by Dariusz Lelek on 9/11/17 10:47 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process.message;

public enum MessageType {
  EMPTY("Empty", MessagePriority.NONE),

  VOICE("Voice", MessagePriority.HIGH),
  SYSTEM("System", MessagePriority.HIGH);

  private final String type;
  private final MessagePriority priority;

  MessageType(String type, MessagePriority priority) {
    this.type = type;
    this.priority = priority;
  }

  public String getType() {
    return type;
  }

  public MessagePriority getPriority() {
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
