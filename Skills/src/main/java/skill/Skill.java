/*
 * Created by Dariusz Lelek on 9/11/17 10:39 PM
 * Copyright (c) 2017. All rights reserved.
 */

package skill;

import dictionary.WordClass;
import process.instruction.Instruction;
import org.apache.log4j.Logger;
import process.instruction.InstructionValidator;
import process.priority.Priority;
import process.processable.Processable;

import java.util.Collection;


public abstract class Skill extends Processable {
  protected final static Logger logger = Logger.getLogger(Skill.class);

  private final Collection<WordClass> expectedWordClasses;

  private InstructionValidator instructionValidator = InstructionValidator.UNKNOWN;
  private Instruction instruction = Instruction.EMPTY;

  public Skill(Priority priority, Collection<WordClass> expectedWordClasses) {
    super.priority = priority;
    this.expectedWordClasses = expectedWordClasses;
  }

  public final void setInstruction(Instruction instruction) {
    this.instruction = instruction;

    if(instruction.hasInstructions()){
      setInstructionValidator();
    }
  }

  private void setInstructionValidator(){
    instructionValidator = SkillProcessHelper
        .instructionHasAllExpectedWordClasses(instruction, expectedWordClasses)
        ? InstructionValidator.VALID : InstructionValidator.INVALID;
  }

  public Instruction getInstruction() {
    return instruction;
  }

  public final boolean hasValidInstruction(){
    return instructionValidator == InstructionValidator.VALID;
  }

  @Override
  public boolean hasInstructions() {
    return instruction.hasInstructions();
  }

  @Override
  public abstract boolean process();

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
