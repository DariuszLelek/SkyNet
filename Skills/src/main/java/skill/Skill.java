/*
 * Created by Dariusz Lelek on 9/11/17 10:39 PM
 * Copyright (c) 2017. All rights reserved.
 */

package skill;

import constant.WordClass;
import process.instruction.Instruction;
import org.apache.log4j.Logger;
import process.instruction.InstructionValidator;
import process.priority.Priority;
import process.processable.Processable;

import java.util.Collection;


public abstract class Skill extends Processable {
  protected final static Logger logger = Logger.getLogger(Skill.class);

  private final Collection<WordClass> expectedWordClasses;

  private InstructionValidator validator = InstructionValidator.UNKNOWN;
  private final boolean voiceConfirmationNeeded;

  protected Skill(Priority priority, Collection<WordClass> expectedWordClasses,
                  boolean voiceConfirmationNeeded) {
    super.priority = priority;

    this.expectedWordClasses = expectedWordClasses;
    this.voiceConfirmationNeeded = voiceConfirmationNeeded;
  }

  public final void setInstruction(Instruction instruction) {
    super.setInstruction(instruction);

    if(!instruction.isEmpty()){
      validateInstruction();
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

  private void validateInstruction() {
    if (expectedWordClasses.isEmpty() ||
        SkillProcessHelper.instructionHasAllExpectedWordClasses(getInstruction(), expectedWordClasses)) {
      validator = InstructionValidator.VALID;
    } else {
      logger.warn(this.toString() + " has invalid instruction.");
      validator = InstructionValidator.INVALID;
    }
  }

  public boolean isVoiceConfirmationNeeded() {
    return voiceConfirmationNeeded;
  }

  public final boolean hasValidInstruction(){
    return validator == InstructionValidator.VALID;
  }

  @Override
  public abstract boolean process();

  @Override
  public int getPriority(){
    return priority.getValue();
  }

}
