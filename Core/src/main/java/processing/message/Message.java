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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import processing.Processable;
import processing.message.handler.HandlerFactory;

/**
 *
 * @author Dariusz Lelek
 */
public class Message implements Processable {
  private final MessageType type;
  private final String header;
  private final DateTime messageTime;

  private boolean isProcessed = false;

  private final List<String> content = new ArrayList<>();

  public Message() {
    this.type = MessageType.EMPTY;
    this.header = "";
    this.messageTime = DateTime.now();

    isProcessed = true;
  }

  Message(MessageType type, String header, DateTime messageTime) {
    this.type = type;
    this.header = header;
    this.messageTime = messageTime;
  }

  public MessageType getType() {
    return type;
  }

  public DateTime getMessageTime() {
    return messageTime;
  }

  public String getHeader() {
    return header;
  }

  public List<String> getContent() {
    return content;
  }

  void addTextToContent(String text){
    content.add(text);
  }

  private String getContentString(){
    return content.stream().collect(Collectors.joining(" "));
  }


  @Override
  public void startProcessing() {
    if(!isProcessed){
      HandlerFactory.getHandlerByMessageType(type).handle(this);
    }
  }

  @Override
  public void stopProcessing() {
    isProcessed = true;
  }

  @Override
  public boolean isProcessed() {
    return isProcessed;
  }

  @Override
  public boolean canBeProcessed() {
    return HandlerFactory.getHandlerByMessageType(type).canHandle();
  }

  @Override
  public int getPriority() {
    return type.getPriority();
  }

  @Override
  public String getInfo() {
    return "Message type: " + type.name() + ", header: " + header + ", content: " + getContentString();
  }

}
