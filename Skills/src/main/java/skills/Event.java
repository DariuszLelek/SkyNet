/*
 * Created by Dariusz Lelek on 9/17/17 3:42 PM
 * Copyright (c) 2017. All rights reserved.
 */

package skills;

import constant.WordClass;
import process.priority.Priority;
import skill.Skill;

import java.util.Collection;
import java.util.LinkedList;

public class Event extends Skill {

  protected Event() {
    super(Priority.MEDIUM, new LinkedList<>(), true);
  }

  @Override
  public boolean process() {
    return false;
  }
}
