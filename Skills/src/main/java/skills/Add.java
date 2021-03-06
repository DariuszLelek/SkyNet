/*
 * Created by Dariusz Lelek on 9/11/17 10:59 PM
 * Copyright (c) 2017. All rights reserved.
 */

package skills;

import constant.WordClass;
import process.priority.Priority;
import skill.Skill;

import java.util.ArrayList;

public class Add extends Skill {

  public Add() {
    super(Priority.MEDIUM, new ArrayList<WordClass>(){{
      add(WordClass.NOUN);
      add(WordClass.NOUN);
    }}, true);
  }

  @Override
  public boolean canProcess() {
    return hasInstruction();
  }

  @Override
  public boolean process() {
    // TODO for thread testing
    return false;
  }
}
