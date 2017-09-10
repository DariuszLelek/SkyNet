/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */

package processing.message.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

import hibernate.Word;
import org.joda.time.DateTime;

import processable.Processable;

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
