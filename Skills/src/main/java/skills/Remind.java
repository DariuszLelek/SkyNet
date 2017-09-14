/*
 * Created by Dariusz Lelek on 9/12/17 6:49 PM
 * Copyright (c) 2017. All rights reserved.
 */

package skills;

import entity.WordClass;
import process.priority.Priority;
import skill.Skill;

import java.util.ArrayList;

public class Remind extends Skill {

  public Remind() {
    super(Priority.MEDIUM, new ArrayList<WordClass>(){{
      add(WordClass.NOUN);
      add(WordClass.VERB);
      add(WordClass.NOUN);
    }});
  }

  @Override
  public boolean process() {
    if(hasValidInstruction()){
      // TODO process
      logger.info("process() - processing Remind: " + getInstruction().toString());

      return true;
    }

    return false;
  }
}
