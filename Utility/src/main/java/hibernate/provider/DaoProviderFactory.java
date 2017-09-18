/*
 * Created by Dariusz Lelek on 9/15/17 10:59 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.provider;

import dao.*;

import java.util.HashMap;
import java.util.Map;

public class DaoProviderFactory {

  public static Provider<WordDao> getWordProvider() {
    return new DaoProvider<>(WordDao.class);
  }

  public static Provider<PersonDao> getPersonProvider() {
    return new DaoProvider<>(PersonDao.class);
  }

  public static Provider<EventDao> getEventProvider() {
    return new DaoProvider<>(EventDao.class);
  }

}
