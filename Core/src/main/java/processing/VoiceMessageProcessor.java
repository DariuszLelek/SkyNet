package processing;

import org.apache.log4j.Logger;
import processable.Processable;
import processing.executor.ProcessableExecutor;
import processing.message.model.Message;
import instruction.Instruction;
import skill.Skill;
import provider.SkillFactory;
import provider.SkillProvider;

public class VoiceMessageProcessor implements Processor {
  private final static Logger logger = Logger.getLogger(VoiceMessageProcessor.class);

  private final SkillProvider skillProvider = SkillFactory.getSkillProvider();

  @Override
  public void process(Processable processable) {
    if(processable instanceof Message){
      process((Message) processable);
    }else{
      logger.warn("process() - processable is not type of " + Message.class.getName());
    }
  }

  // process one voice message at time
  public void process(final Message message) {
    if(message.canBeProcessed()){
      String messageChunk = peekMessageChunk(message);

      if(skillProvider.hasSkill(messageChunk)){
        handleMessageAsSkill(message, messageChunk);
      }else{
        dequeueChunkAndTryAgain(message);
      }
    }
  }

  private void handleMessageAsSkill(Message message, String skillName){
    logger.info("handleMessageAsSkill() - " + skillName);

    Skill skill = skillProvider.getSkill(skillName);
    skill.setInstruction(new Instruction(message.getWords(), null));
    ProcessableExecutor.addProcessable(skill);
  }

  private String peekMessageChunk(final Message message){
    return !message.getWords().isEmpty() ? message.getWords().peek().getWord() : "";
  }

  private void dequeueChunkAndTryAgain(final Message message){
    message.getWords().poll();
    process(message);
  }
}
