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
package processing.message;

import java.util.Queue;
import org.joda.time.DateTime;

/**
 *
 * @author Dariusz Lelek
 */
public class Message {
  private final MessageType type;
  // TODO add priority here?
  private final DateTime messageTime;
  
  private  Queue<String> contentQueue;

  public Message(MessageType type) {
    this.type = type;
    this.messageTime = DateTime.now();
  }

  public MessageType getType() {
    return type;
  }

  public DateTime getMessageTime() {
    return messageTime;
  }

  public Queue<String> getContentQueue() {
    return contentQueue;
  }

  public void setContentQueue(Queue<String> contentQueue) {
    this.contentQueue = contentQueue;
  }
}
