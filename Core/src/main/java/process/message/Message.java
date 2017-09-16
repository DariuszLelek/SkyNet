/*
 * Created by Dariusz Lelek on 9/11/17 10:47 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process.message;

import org.joda.time.DateTime;
import process.ProcessorFactory;
import process.processable.Processable;
import skill.SkillFactory;
import skill.Skill;

public class Message extends Processable {

  private final MessageType type;
  private final DateTime messageTime = DateTime.now();

  public Message(MessageType type) {
    this.type = type;
  }

  public MessageType getType() {
    return type;
  }

  public DateTime getMessageTime() {
    return messageTime;
  }


  @Override
  public boolean process() {
    final String chunk = getInstruction().poll();

    if(SkillFactory.hasSkill(chunk)){
      final Skill skill = SkillFactory.getSkill(chunk);
      ProcessorFactory.getSkillProcessor().process(skill, getInstruction());
    }else{
      ProcessorFactory.getMessageProcessor().process(this);
    }

    return true;
  }

  @Override
  public int getPriority() {
    return type.getPriority().getValue();
  }

  @Override
  public String toString() {
    return "Message{" + type + ", " + getInstruction() + "}";
  }
}
