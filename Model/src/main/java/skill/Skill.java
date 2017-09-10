/*
 * Created by Dariusz Lelek on 9/10/17 9:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

package skill;

import instruction.Instruction;
import processable.Processable;


public class Skill extends Processable {
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
  public boolean canBeProcessed() {
    return false;
  }

  @Override
  public int getPriorityValue() {
    return priority.getValue();
  }

  @Override
  public String toString() {
    return "Skill: " + this.getClass().getName()
        + " " + priority.toString()
        + " " + instruction.toString();
  }

}
