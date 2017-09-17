/*
 * Created by Dariusz Lelek on 9/17/17 1:12 AM
 * Copyright (c) 2017. All rights reserved.
 */

package helper;

import dao.RemindDao;
import hibernate.preserver.DaoPreserverFactory;
import model.Duration;
import org.joda.time.DateTime;
import model.Validator;
import utilities.time.TimeUtility;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class RemindHelper {
  private RemindDao remindDao = null;

  public RemindDao getRemindDao(){
    return remindDao;
  }

  public boolean saveRemindDao(){
    if(isValid()){
      DaoPreserverFactory.getRemindPreserver().save(remindDao);
      return true;
    }
    return false;
  }

  public void dequeueMessage(Queue<String> chunks){
    String contentCandidate = "";

    // TODO add word processing to get valid sentence
    getRemindDate(chunks);

    while(!chunks.isEmpty()){
      String chunk = chunks.poll();


    }
  }

  private DateTime getRemindDate(Collection<String> chunks){
    DateTime date = null;

    Map<String, Duration> wordToDurationMap = TimeUtility.getWordToDurationMap(chunks);

    return null;

  }

  private void mapDurationsToWords(){

  }

  private boolean isValid() {
    return remindDao != null && ((remindDao.getRepeatDelayS() == 0 && remindDao.getTime() != null) ||
        remindDao.getRepeatDelayS() > 0) && !remindDao.getText().isEmpty();
  }
}
