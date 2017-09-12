/*
 * Created by Dariusz Lelek on 9/11/17 10:47 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process.message;

import java.util.LinkedList;
import java.util.Queue;

import org.joda.time.DateTime;
import processable.Processable;

public class Message extends Processable {

  private final MessageType type;
  private final DateTime messageTime = DateTime.now();

  private final Queue<String> words = new LinkedList<>();

  public Message(MessageType type) {
    this.type = type;
  }

  public MessageType getType() {
    return type;
  }

  public DateTime getMessageTime() {
    return messageTime;
  }

  public Queue<String> getWords() {
    return words;
  }

  public void addWord(String word){
    words.add(word);
  }

  @Override
  public void execute() {
    //TODO
  }

  @Override
  public boolean canProcess() {
    return !words.isEmpty();
  }

  @Override
  public int getPriority() {
    return type.getPriority().getValue();
  }
}
