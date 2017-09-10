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

import processing.Processor;
import processing.message.model.Message;
import processing.message.model.MessageType;

/**
 *
 * @author Dariusz Lelek
 */
public class MessageProcessor extends Processor{
  private final MessageCreator messageCreator = new MessageCreator();

  public void processVoiceMessage(String messageText){
    super.processText(messageText);
    processMessage(messageCreator.getMessage(messageText, MessageType.VOICE));
  }

  private void processMessage(final Message message){
    super.process(message);
  }
}
