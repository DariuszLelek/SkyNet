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

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Dariusz Lelek
 */
public class MessageCreator {

  public Queue<Message> getMessages(String messageText, MessagePriority priority){
    Queue<Message> messages = new LinkedList<>();
    
    
    Queue<String> messageChunks = getMessageChunks(messageText);
    
    while(!messageChunks.isEmpty()){
      String dequeuedChunk = messageChunks.poll();
      
      //if(dequeuedChunk)
    }
    
    //List<String> messageContent = textMessage.split(messageText)

    return messages;
  }
  
  private boolean isTextOfMessageType(String text){
    return false;
  }

  private Queue<String> getMessageChunks(String messageText) {
    Queue<String> messageChunks = new LinkedList<>();

    if (!messageText.isEmpty()) {
      String[] chunks = messageText.split("\\s+");
      for (String chunk : chunks) {
        if (!chunk.isEmpty()) {
          messageChunks.add(chunk.replaceAll("[^A-Za-z0-9]", ""));
        }
      }
    }

    return messageChunks;
  }
  
  private MessageType getMessageType(Queue<String> messageChunks){
    MessageType messageType = MessageType.EMPTY;
      
    // dequeue chunks
    
    return messageType;
  }
  
  
  
  
}
