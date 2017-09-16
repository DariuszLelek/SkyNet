/*
 * Created by Dariusz Lelek on 9/16/17 12:08 AM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.preserver;

import dao.PersonDao;
import dao.RemindDao;
import dao.TimeDao;
import dao.WordDao;

public class DaoPreserverFactory {
  public static Preserver<WordDao> getWordPreserver() {
    return new DaoPreserver<>();
  }

  public static Preserver<TimeDao> getTimePreserver() {
    return new DaoPreserver<>();
  }

  public static Preserver<PersonDao> getPersonPreserver() {
    return new DaoPreserver<>();
  }

  public static Preserver<RemindDao> getRemindPreserver() {
    return new DaoPreserver<>();
  }
}
