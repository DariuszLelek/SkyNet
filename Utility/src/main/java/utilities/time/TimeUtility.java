/*
 * Created by Dariusz Lelek on 9/17/17 2:35 AM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities.time;

import model.Duration;
import constant.Month;
import constant.TimeUnit;
import utilities.StringUtility;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TimeUtility {
  private static final String[] repeatableKeyWords = {"every", "each"};
  private static final Map<String, TimeUnit> CACHE_TIME_UNIT = new HashMap<>();
  private static final Map<String, Month> CACHE_MONTH = new HashMap<>();

  static{
    initCache();
  }

  public static Month getMonth(String string){
    return CACHE_MONTH.getOrDefault(CACHE_MONTH.keySet().stream()
        .filter(unit -> StringUtility.containsIgnoreCase(string, unit))
        .findFirst().orElse(""), Month.UNKNOWN);
  }

  public static TimeUnit getTimeUnit(String string){
    return CACHE_TIME_UNIT.getOrDefault(CACHE_TIME_UNIT.keySet().stream()
        .filter(unit -> StringUtility.containsIgnoreCase(string, unit))
        .findFirst().orElse(""), TimeUnit.NONE);
  }

  private static Duration getDurationFromString(String string){
    Duration duration = new Duration(getTimeUnit(string));
    return duration.isValid() ? duration : getMonth(string).getDuration();
  }

  public static boolean isMonth(String text){
    return CACHE_MONTH.containsKey(text);
  }

  private static void initCache(){
    Arrays.stream(TimeUnit.values()).forEach(value -> CACHE_TIME_UNIT.put(value.name().toLowerCase(), value));
    Arrays.stream(Month.values()).forEach(value -> CACHE_MONTH.put(value.name().toLowerCase(), value));
  }

  public static Map<String, Duration> getWordToDurationMap(Collection<String> collection){
    return collection.stream()
        .collect(Collectors.toMap(word -> word, TimeUtility::getDurationFromString, (word, duration) -> word));
  }
}
