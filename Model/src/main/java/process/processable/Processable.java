/*
 * Created by Dariusz Lelek on 9/12/17 11:50 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process.processable;

import process.instruction.Instruction;
import process.instruction.InstructionValidator;
import process.priority.Priority;

public abstract class Processable {

  private Instruction instruction = new Instruction();

  protected Priority priority;

  boolean active = true;

  public final boolean isActive(){
    return this.active;
  }

  public final void disable(){
    this.active = false;
  }

  public final Instruction getInstruction(){
    return instruction;
  }

  public final boolean hasInstructions(){
    return !instruction.isEmpty();
  }

  public void setInstruction(final Instruction instruction){
    this.instruction = instruction;
  }

  public abstract boolean process();

  public abstract int getPriority();

  @Override
  public String toString() {
    return "Processable{" + this.getClass().getName() + ", " + instruction + "}";
  }
}
