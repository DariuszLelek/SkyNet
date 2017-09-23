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

  public static HibernateUtility getByDatabaseConfig(DataBaseConfig schema) {
    return getFromCache(schema);
  }

  private static HibernateUtility getFromCache(DataBaseConfig dataBaseConfig){
    synchronized (CACHE){
      if(!CACHE.containsKey(dataBaseConfig) || CACHE.get(dataBaseConfig).isSessionFactoryClosed()){
        CACHE.put(dataBaseConfig, new HibernateUtility((dataBaseConfig)));
      }

      return CACHE.get(dataBaseConfig);
    }
  }

  public static void closeAllAndClear(){
    synchronized (CACHE){
      CACHE.values().forEach(HibernateUtility::closeSessionFactory);
      CACHE.clear();
    }
  }


  public static synchronized boolean isSessionFactoryOpen(){
    return CACHE.values().stream().anyMatch(utility -> !utility.isSessionFactoryClosed());
  }
}
