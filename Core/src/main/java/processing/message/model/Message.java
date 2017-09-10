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

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

import hibernate.Word;
import org.joda.time.DateTime;

import processable.Processable;

/**
 *
 * @author Dariusz Lelek
 */
public class Message extends Processable {

  private final MessageType type;
  private final DateTime messageTime = DateTime.now();

  private final Queue<Word> words = new LinkedList<>();

  public Message(MessageType type) {
    this.type = type;
  }

  public MessageType getType() {
    return type;
  }

  public DateTime getMessageTime() {
    return messageTime;
  }

  public Queue<Word> getWords() {
    return words;
  }

  public void addWord(Word word){
    words.add(word);
  }

  @Override
  public void execute() {
    //TODO
  }

  @Override
  public boolean canBeProcessed() {
    return !words.isEmpty();
  }

  @Override
  public int getPriorityValue() {
    return type.getPriority().getValue();
  }

  @Override
  public String toString() {
    return "Message: " + type.toString() + ", words: " + getWordsString();
  }

  private String getWordsString(){
    return words.stream().map(Word::getWord).collect(Collectors.joining(", "));
  }
}
