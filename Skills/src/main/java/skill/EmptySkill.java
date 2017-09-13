/*
 * Created by Dariusz Lelek on 9/12/17 7:06 PM
 * Copyright (c) 2017. All rights reserved.
 */

package skill;

import dictionary.WordClass;
import process.priority.Priority;

import java.util.ArrayList;

public class EmptySkill extends Skill {

  public EmptySkill() {
    super(Priority.NONE, new ArrayList<>());
  }

  @Override
  public boolean process() {
    return true;
  }
}
