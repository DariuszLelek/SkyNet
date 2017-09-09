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

import org.joda.time.DateTime;
import processing.message.match.Matcher;
import processing.message.match.MessageMatcherFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 *
 * @author Dariusz Lelek
 */
public class MessageCreator {

  public Queue<Message> getMessages(final String messageText) {
    final Queue<String> messageChunks = getMessageChunks(messageText != null ? messageText : "");
    return getMessagesFromChunks(messageChunks);
  }

  private Queue<Message> getMessagesFromChunks(final Queue<String> messageChunks){
    final Queue<Message> messages = new LinkedList<>();

    while (!messageChunks.isEmpty()) {
      String dequeuedChunk = messageChunks.poll();

      addChunkToMessages(dequeuedChunk, messages);
      tryInsertNewMessageFromChunk(dequeuedChunk, messages);
    }

    return messages;
  }

  private void tryInsertNewMessageFromChunk(String chunk, Queue<Message> messages){
    for(Matcher matcher : MessageMatcherFactory.getMatchers()){
      String match = matcher.getMatch(chunk);
      if(!match.isEmpty()){
        messages.add(new Message(matcher.getMessageType(), match, DateTime.now()));
        return;
      }
    }
  }

  private void addChunkToMessages(String chunk, Queue<Message> messages){
    messages.forEach(message -> message.addTextToContent(chunk));
  }

  private Queue<String> getMessageChunks(String messageText) {
    return new LinkedList<>(
        Arrays.stream(messageText.split("\\s+"))
            .filter(chunk -> !chunk.isEmpty())
            .map(x -> x.replaceAll("[^A-Za-z0-9]", ""))
            .collect(Collectors.toList()));
  }
}
