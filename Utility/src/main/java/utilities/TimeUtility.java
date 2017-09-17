/*
 * Created by Dariusz Lelek on 9/16/17 4:34 PM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import constant.Month;
import constant.TimeUnit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TimeUtility {
  private static final String[] repeatableKeyWords = {"every", "each"};
  private static final Map<String, TimeUnit> CACHE_TIME_UNIT = new HashMap<>();
  private static final Map<String, Month> CACHE_MONTH = new HashMap<>();

  static{
    initCache();
  }

  public static TimeUnit getTimeUnit(String string){
    return CACHE_TIME_UNIT.getOrDefault(string, TimeUnit.NOW);
  }

  public static boolean isTimeUnit(String text){
    return CACHE_TIME_UNIT.containsKey(text);
  }

  public static boolean isMonth(String text){
    return CACHE_MONTH.containsKey(text);
  }

  private static void initCache(){
    Arrays.stream(TimeUnit.values()).forEach(value -> CACHE_TIME_UNIT.put(value.name().toLowerCase(), value));
    Arrays.stream(Month.values()).forEach(value -> CACHE_MONTH.put(value.name().toLowerCase(), value));
  }

}
