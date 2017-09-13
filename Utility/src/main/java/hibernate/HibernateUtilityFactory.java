/*
 * Created by Dariusz Lelek on 9/11/17 7:39 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate;

import config.DataBaseConfig;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtilityFactory {

  private static final Map<DataBaseConfig, HibernateUtility> CACHE = new HashMap<>();

  static {
    CACHE.put(DataBaseConfig.PROD, new HibernateUtility(DataBaseConfig.PROD));
  }

  public static HibernateUtility getByDatabaseConfig(DataBaseConfig schema) {
    return getFromCache(schema);
  }

  private static HibernateUtility getFromCache(DataBaseConfig dataBaseConfig){
    return CACHE.getOrDefault(dataBaseConfig, CACHE.get(DataBaseConfig.PROD));
  }

  public static void closeAllSessionFactories(){
    CACHE.values().forEach(HibernateUtility::closeSessionFactory);
  }
}
