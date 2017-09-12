/*
 * Created by Dariusz Lelek on 9/11/17 10:59 PM
 * Copyright (c) 2017. All rights reserved.
 */

package skills;

import instruction.Instruction;
import priority.Priority;
import skill.Skill;

public class Add extends Skill {

  public Add() {
    super(Priority.MEDIUM);
  }

  @Override
  public void execute() {
  }

  @Override
  public boolean canProcess() {
    return true;
  }
}
