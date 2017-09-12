/*
 * Created by Dariusz Lelek on 9/12/17 12:14 AM
 * Copyright (c) 2017. All rights reserved.
 */

package process.message;

import instruction.Instruction;
import org.apache.log4j.Logger;
import processable.Processable;
import process.Processor;
import process.ProcessorFactory;
import provider.SkillFactory;
import provider.SkillProvider;
import skill.Skill;

public class MessageProcessor extends Processor{

  private final static Logger logger = Logger.getLogger(MessageProcessor.class);

  private final SkillProvider skillProvider = SkillFactory.getSkillProvider();

  @Override
  public void process(Processable processable) {
    if(processable.canProcess()) {
      process((Message) processable);
    }
  }

  // execute one message at time
  private void process(final Message message) {
      String messageChunk = peekMessageChunk(message);

      // TODO copy message here to persist original one?

      if(skillProvider.hasSkill(messageChunk)){
        processMessageAsSkill(message, messageChunk);
      }else{
        dequeueChunkAndTryAgain(message);
      }

      //TODO when all prev condition failed just execute message - message.execute(); // ask google or sth
  }

  private void processMessageAsSkill(Message message, String skillName){
    logger.info("processMessageAsSkill() - " + skillName);

    Skill skill = skillProvider.getSkill(skillName);
    skill.setInstruction(new Instruction(message.getWords()));
    ProcessorFactory.getSkillProcessor().process(skill);
  }

  private String peekMessageChunk(final Message message){
    return !message.getWords().isEmpty() ? message.getWords().peek() : "";
  }

  private void dequeueChunkAndTryAgain(final Message message){
    message.getWords().poll();
    process(message);
  }
}
