/*
 * Created by Dariusz Lelek on 9/17/17 4:31 PM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import constant.TimeKeyWord;
import constant.TimeUnit;
import exception.ListsLengthMissMatchException;
import org.joda.time.DateTime;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TimeUtility {

  private static final String[] repeatableKeyWords = {"every", "each"};

  private static final Collection<String> timeKeyWords = new ArrayList<String>();

  private static final Map<String, TimeUnit> CACHE = new HashMap<>();
  private static final Map<String, TimeKeyWord> CACHE_KEY_WORDS = new HashMap<>();

  static{
    initCache();
    initTimeKeyWords();
  }

  public static DateTime getDateTime(List<String> strings){
    if(!strings.isEmpty()){
      List<TimeUnit> timeUnitList = getTimeUnitList(strings);
      // List<Boolean> repeatableList = getRepeatableList(strings, timeUnitList);
      List<Long> numberList = getNumberList(strings);
      List<TimeKeyWord> timeKeyWordList = getTimeKeyWordList(strings);


      DateTime t = DateTime.now();

      t = t.plus(TimeUnit.DAY.getDuration() * 1000);
      t = t.withHourOfDay(5);

      // TODO
      // merge lists and try get date from them


      return null;
    }
    return null;
  }

  private int tryGetHour(){
    // TODO
    return 1;
  }

  public static DateTime getDateTime(String[] strings){
    return getDateTime(Arrays.asList(strings));
  }

  public static TimeUnit getTimeUnit(String string){
    return CACHE.getOrDefault(string.toLowerCase(), tryGetBestMatch(string));
  }

  public static boolean isTimeUnit(String string){
    return CACHE.keySet().stream()
        .anyMatch(unit -> StringUtility.containsIgnoreCase(string, unit));
  }

  private static List<Long> getNumberList(List<String> strings){
    return Collections.unmodifiableList(
        strings.stream()
            .map(NumberUtility::tryGetNumberFromWord)
            .collect(Collectors.toList()));
  }

  private static List<TimeKeyWord> getTimeKeyWordList(List<String> strings){
    return Collections.unmodifiableList(
        strings.stream()
            .map(s -> CACHE_KEY_WORDS.getOrDefault(s.toLowerCase(), TimeKeyWord.UNKNOWN))
            .collect(Collectors.toList()));
  }

  private static List<Boolean> getRepeatableList(List<String> strings, List<TimeUnit> timeUnitList) {
    return Collections.unmodifiableList(
        IntStream.range(0, strings.size())
            .mapToObj(i -> i < timeUnitList.size() && timeUnitList.get(i).isRepeatable()
                || i - 1 >= 0 && StringUtility.containsIgnoreCase(strings.get(i - 1), repeatableKeyWords))
            .collect(Collectors.toList()));
  }

  private static List<TimeUnit> getTimeUnitList(Collection<String> strings){
    return Collections.unmodifiableList(
        strings.stream()
            .map(TimeUtility::getTimeUnit)
            .collect(Collectors.toList()));
  }

  private static TimeUnit tryGetBestMatch(String string){
    return CACHE.getOrDefault(CACHE.keySet().stream()
        .filter(unit -> StringUtility.containsIgnoreCase(string, unit))
        .findFirst().orElse(""), TimeUnit.UNKNOWN);
  }

  private static void initCache(){
    Arrays.stream(TimeUnit.values()).forEach(value -> CACHE.put(value.name().toLowerCase(), value));
    Arrays.stream(TimeKeyWord.values()).forEach(value -> CACHE_KEY_WORDS.put(value.getValue().toLowerCase(), value));
  }

  private static void initTimeKeyWords(){
    timeKeyWords.addAll(Arrays.stream(TimeKeyWord.values())
        .map(TimeKeyWord::getValue).collect(Collectors.toList()));
  }
}
