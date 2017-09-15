/*
 * Created by Dariusz Lelek on 9/15/17 10:59 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.provider;

import dao.*;

public class DaoProviderFactory {
  public static Provider<WordDao> getWordProvider() {
    return new DaoProvider<>(WordDao.class);
  }

  public static Provider<TimeDao> getTimeProvider() {
    return new DaoProvider<>(TimeDao.class);
  }

  public static Provider<PersonDao> getPersonProvider() {
    return new DaoProvider<>(PersonDao.class);
  }

  public static Provider<RemindDao> getRemindProvider() {
    return new DaoProvider<>(RemindDao.class);
  }
}
