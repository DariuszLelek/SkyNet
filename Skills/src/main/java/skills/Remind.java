/*
 * Created by Dariusz Lelek on 9/12/17 6:49 PM
 * Copyright (c) 2017. All rights reserved.
 */

package skills;

import instruction.Instruction;
import priority.Priority;
import skill.Skill;

public class Remind extends Skill {

  public Remind() {
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
