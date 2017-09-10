package processing;

import processing.message.MessageProcessor;
import processing.skill.SkillProcessor;

public class ProcessorFactory {
  private final static MessageProcessor messageProcessor = new MessageProcessor();
  private final static SkillProcessor skillProcessor = new SkillProcessor();

  public static MessageProcessor getMessageProcessor() {
    return messageProcessor;
  }

  public static SkillProcessor getSkillProcessor() {
    return skillProcessor;
  }
}
