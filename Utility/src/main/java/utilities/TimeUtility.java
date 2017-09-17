/*
 * Created by Dariusz Lelek on 9/17/17 4:31 PM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import constant.Time;
import utilities.StringUtility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TimeUtility {
  private static final String[] repeatableKeyWords = {"every", "each"};
  private static final Map<String, Time> CACHE = new HashMap<>();

  static{
    initCache();
  }

  public static Time getTime(String string){
    return CACHE.getOrDefault(string.toLowerCase(), tryGetBestMatch(string));
  }

  public static boolean isTime(String string){
    return CACHE.keySet().stream()
        .anyMatch(unit -> StringUtility.containsIgnoreCase(string, unit));
  }

  private static Time tryGetBestMatch(String string){
    return CACHE.getOrDefault(CACHE.keySet().stream()
        .filter(unit -> StringUtility.containsIgnoreCase(string, unit))
        .findFirst().orElse(""), Time.UNKNOWN);
  }

  private static void initCache(){
    Arrays.stream(Time.values()).forEach(value -> CACHE.put(value.name().toLowerCase(), value));
  }
}
