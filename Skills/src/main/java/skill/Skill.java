/*
 * Created by Dariusz Lelek on 9/11/17 10:39 PM
 * Copyright (c) 2017. All rights reserved.
 */

package skill;

import instruction.Instruction;
import org.apache.log4j.Logger;
import processable.Processable;


public class Skill extends Processable {
  protected final static Logger logger = Logger.getLogger(Skill.class);

  private SkillPriority priority = SkillPriority.NONE;
  private Instruction instruction = Instruction.EMPTY;

  public Skill() {
  }

  void setPriority(SkillPriority priority) {
    this.priority = priority;
  }

  public Instruction getInstruction() {
    return instruction;
  }

  public void setInstruction(Instruction instruction) {
    this.instruction = instruction;
  }


  @Override
  public void execute() {
  }

  @Override
  public boolean canProcess() {
    return false;
  }

  @Override
  public int getPriority() {
    return priority.getValue();
  }

  @Override
  public String toString() {
    return "Skill: " + this.getClass().getName()
        + " " + priority.toString()
        + " " + instruction.toString();
  }

}
