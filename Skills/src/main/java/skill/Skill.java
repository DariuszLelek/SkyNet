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

  protected Skill(Priority priority, Collection<WordClass> expectedWordClasses) {
    super.priority = priority;
    this.expectedWordClasses = expectedWordClasses;
  }

  public final void setInstruction(Instruction instruction) {
    super.setInstruction(instruction);

    if(!instruction.isEmpty()){
      setInstructionValidator();
    }
  }

  public final void logSuccess(String message){
    logger.info(this.getClass().getName() + " SUCCESS " + message);
  }

  public final void logFail(String message){
    logger.warn(this.getClass().getName() + " FAIL " + message);
  }

  public final void logFail(String message, Throwable t){
    logger.error(this.getClass().getName() + " FAIL " + message, t);
  }

  private void setInstructionValidator(){
    instructionValidator = SkillProcessHelper
        .instructionHasAllExpectedWordClasses(getInstruction(), expectedWordClasses)
        ? InstructionValidator.VALID : InstructionValidator.INVALID;
  }

  public final boolean hasValidInstruction(){
    return instructionValidator == InstructionValidator.VALID;
  }

  @Override
  public abstract boolean process();

  @Override
  public int getPriority(){
    return priority.getValue();
  }

}
