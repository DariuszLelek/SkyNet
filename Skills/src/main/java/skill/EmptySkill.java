/*
 * Created by Dariusz Lelek on 9/12/17 7:06 PM
 * Copyright (c) 2017. All rights reserved.
 */

package skill;

import instruction.Instruction;
import priority.Priority;

public class EmptySkill extends Skill {

  public EmptySkill() {
    super(Priority.NONE);
  }

  @Override
  public void execute() {

  }

  @Override
  public boolean canProcess() {
    return false;
  }
}
