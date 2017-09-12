/*
 * Created by Dariusz Lelek on 9/11/17 10:39 PM
 * Copyright (c) 2017. All rights reserved.
 */

package skill;

import instruction.Instruction;
import org.apache.log4j.Logger;
import priority.Priority;
import processable.Processable;


public abstract class Skill extends Processable {
  protected final static Logger logger = Logger.getLogger(Skill.class);

  private Instruction instruction = Instruction.EMPTY;

  public Skill(Priority priority) {
    super.priority = priority;
  }

  public void setInstruction(Instruction instruction) {
    this.instruction = instruction;
  }

  public Instruction getInstruction() {
    return instruction;
  }

  @Override
  public abstract boolean process();

  @Override
  public abstract boolean canProcess();

  @Override
  public int getPriority(){
    return priority.getValue();
  }

  @Override
  public String toString() {
    return "Skill: " + this.getClass().getName()
        + " " + priority.toString()
        + " " + instruction.toString();
  }

}
