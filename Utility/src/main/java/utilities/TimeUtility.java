/*
 * Created by Dariusz Lelek on 9/17/17 4:31 PM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import constant.Time;
import org.joda.time.DateTime;
import utilities.number.NumberFromString;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TimeUtility {
  private static final String[] repeatableKeyWords = {"every", "each"};
  private static final Map<String, Time> CACHE = new HashMap<>();

  static{
    initCache();
  }

  public static DateTime getDate(List<String> strings){
    if(!strings.isEmpty()){
      List<Time> timeList = getTimeList(strings);
      List<Boolean> repeatableList = getRepeatableList(strings, timeList);
      List<Long> numberList = getLongList(strings);

      // TODO
      return null;
    }
    return null;
  }

  public static DateTime getDate(String[] strings){
    return getDate(Arrays.asList(strings));
  }

  public static Time getTime(String string){
    return CACHE.getOrDefault(string.toLowerCase(), tryGetBestMatch(string));
  }

  public static boolean isTime(String string){
    return CACHE.keySet().stream()
        .anyMatch(unit -> StringUtility.containsIgnoreCase(string, unit));
  }

  private static List<Long> getLongList(List<String> strings){
    return Collections.unmodifiableList(
        strings.stream()
            .map(NumberUtility::tryGetNumberFromWord)
            .collect(Collectors.toList()));
  }

  private static List<Boolean> getRepeatableList(List<String> strings, List<Time> timeList) {
    return Collections.unmodifiableList(
        IntStream.range(0, strings.size())
            .mapToObj(i -> i < timeList.size() && timeList.get(i).isRepeatable()
                || i - 1 >= 0 && StringUtility.containsIgnoreCase(strings.get(i - 1), repeatableKeyWords))
            .collect(Collectors.toList()));
  }

  private static List<Time> getTimeList(Collection<String> strings){
    return Collections.unmodifiableList(
        strings.stream()
            .map(TimeUtility::getTime)
            .collect(Collectors.toList()));
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
