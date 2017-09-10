/*
 * Copyright 2017 Dariusz Lelek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package processing.message.model;

/**
 *
 * @author Dariusz Lelek
 */
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
