/*
 * Created by Dariusz Lelek on 9/12/17 6:49 PM
 * Copyright (c) 2017. All rights reserved.
 */

package skills;

import constant.WordClass;
import process.priority.Priority;
import skill.Skill;

import java.util.ArrayList;

public class Remind extends Skill {

  public Remind() {
    super(Priority.MEDIUM, new ArrayList<WordClass>(){{
      add(WordClass.NOUN);
      add(WordClass.NOUN);
    }}, true);
  }

  @Override
  public boolean process() {
//    if(hasValidInstruction()){
//      // TODO basically store to DB then run daemon to execute any reminder that is relevant
//      logger.info("process - processing Remind: " + getInstruction().toString());
//      remindHelper.dequeueMessage(getInstruction().getQueue());
//      if(remindHelper.saveRemindDao()){
//        logSuccess("Save:" + remindHelper.getRemindDao());
//        // TODO voice confirmation
//        return true;
//      }
//      logFail("Save:" + remindHelper.getRemindDao());
//      disable();
//      return false;
//    }
//    logFail("Invalid Instruction:" + getInstruction().toString());

    disable();
    return false;
  }
}
