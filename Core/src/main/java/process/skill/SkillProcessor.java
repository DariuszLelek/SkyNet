/*
 * Created by Dariusz Lelek on 9/12/17 12:31 AM
 * Copyright (c) 2017. All rights reserved.
 */

package process.skill;

import instruction.Instruction;
import org.apache.log4j.Logger;
import process.Processor;
import skill.Skill;

public class SkillProcessor extends Processor{

  private final static Logger logger = Logger.getLogger(SkillProcessor.class);

  public void process(Skill skill) {
    super.process(skill);
  }

  public void process(Skill skill, Instruction instruction) {
    skill.setInstruction(instruction);
    process(skill);
  }
}
