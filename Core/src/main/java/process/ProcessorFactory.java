/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process;

import process.message.MessageProcessor;
import process.skill.SkillProcessor;

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
