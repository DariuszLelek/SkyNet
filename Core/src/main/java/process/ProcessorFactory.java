/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process;

import process.message.MessageProcessor;
import process.skill.SkillProcessor;

public class ProcessorFactory {
  private final static Processor messageProcessor = new MessageProcessor();
  private final static Processor skillProcessor = new SkillProcessor();

  public static Processor getMessageProcessor() {
    return messageProcessor;
  }

  public static Processor getSkillProcessor() {
    return skillProcessor;
  }
}
