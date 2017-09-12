/*
 * Created by Dariusz Lelek on 9/11/17 10:47 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process.message;

import java.util.LinkedList;
import java.util.Queue;

import instruction.Instruction;
import org.joda.time.DateTime;
import process.ProcessorFactory;
import processable.Processable;
import provider.SkillFactory;
import skill.Skill;

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
  public boolean process() {
    final String chunk = words.poll();

    if(SkillFactory.getSkillProvider().hasSkill(chunk)){
      final Skill skill = SkillFactory.getSkillProvider().getSkill(chunk);
      ProcessorFactory.getSkillProcessor().process(skill, new Instruction(words));
    }else{
      ProcessorFactory.getMessageProcessor().process(this);
    }

    return true;
  }

  @Override
  public boolean canProcess() {
    return !words.isEmpty();
  }

  @Override
  public int getPriority() {
    return type.getPriority().getValue();
  }

  @Override
  public String toString() {
    return "Message [" + type.toString() + "] , " +
        "words{" + String.join("," + words) + "}";
  }
}
