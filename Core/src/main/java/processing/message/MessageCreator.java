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

import dictionary.DictionaryFactory;
import hibernate.Word;
import processing.message.model.Message;
import processing.message.model.MessageType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 *
 * @author Dariusz Lelek
 */
public class MessageCreator {

  public Message getMessage(final String messageText, final MessageType messageType) {
    final Message message = new Message(messageType);
    final Queue<String> messageChunks = getMessageChunks(validateMessageText(messageText));
    final Queue<Word> words = getWordsFromChunks(messageChunks);

    dequeueWordsIntoMessage(words, message);

    return message;
  }

  private void dequeueWordsIntoMessage(final Queue<Word> messageWords, final Message message){
    messageWords.forEach(message::addWord);
  }

  private Queue<Word> getWordsFromChunks(final Queue<String> messageChunks) {
    return messageChunks.stream()
        .map(chunk -> DictionaryFactory.getWordProvider().getWord(chunk))
        .collect(Collectors.toCollection(LinkedList::new));
  }

  private Queue<String> getMessageChunks(String messageText) {
    return new LinkedList<>(
        Arrays.stream(messageText.split("\\s+"))
            .filter(chunk -> !chunk.isEmpty())
            .map(x -> x.replaceAll("[^A-Za-z0-9]", ""))
            .collect(Collectors.toList()));
  }

  private String validateMessageText(String messageText){
    return messageText != null ? messageText : "";
  }
}
