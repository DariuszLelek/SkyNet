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
}
