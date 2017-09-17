/*
 * Created by Dariusz Lelek on 9/17/17 1:12 AM
 * Copyright (c) 2017. All rights reserved.
 */

package helper;

import dao.RemindDao;
import hibernate.preserver.DaoPreserverFactory;
import utilities.TimeUtility;
import validator.Validator;

import java.util.Collection;
import java.util.Queue;

public class RemindHelper implements Validator<RemindDao>{
  private RemindDao remindDao = null;

  public RemindDao getRemindDao(){
    return remindDao;
  }

  public boolean saveRemindDao(){
    if(isValid(remindDao)){
      DaoPreserverFactory.getRemindPreserver().save(remindDao);
      return true;
    }
    return false;
  }

  public void dequeueMessage(Queue<String> chunks){
    String contentCandidate = "";

    // TODO add word processing to get valid sentence

    while(!chunks.isEmpty()){
      String chunk = chunks.poll();


    }
  }

  @Override
  public boolean isValid(RemindDao object) {
    return object != null && ((object.getRepeatDelayS() == 0 && object.getTime() != null) ||
        object.getRepeatDelayS() > 0) && !object.getText().isEmpty();
  }
}
