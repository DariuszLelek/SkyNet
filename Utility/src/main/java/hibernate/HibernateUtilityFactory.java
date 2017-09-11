/*
 * Created by Dariusz Lelek on 9/11/17 7:39 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate;

import config.DataBaseSchema;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtilityFactory {

  private static final Map<DataBaseSchema, HibernateUtility> CACHE = new HashMap<>();

  public static HibernateUtility getBySchema(DataBaseSchema schema) {
    return getFromCache(schema);
  }

  private static HibernateUtility getFromCache(DataBaseSchema schema){
    if(!CACHE.containsKey(schema)){
      CACHE.put(schema, new HibernateUtility(schema));
    }
    return CACHE.get(schema);
  }


  // TODO provide a way to close session factory for each active DB connection from one place
  private static void closeSessionFactory(DataBaseSchema schema){
    if(CACHE.containsKey(schema)){
      CACHE.get(schema).closeSessionFactory();
    }
  }

  private static void closeAllSessionFactories(){
    CACHE.values().forEach(HibernateUtility::closeSessionFactory);
  }
}
