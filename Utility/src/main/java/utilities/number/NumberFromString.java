/*
 * Created by Dariusz Lelek on 9/16/17 11:56 PM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities.number;

import dao.WordDao;
import constant.Number;
import constant.WordClass;
import helper.entity.WordHelper;
import process.candidate.StringCandidate;
import utilities.StringUtility;

import java.util.*;
import java.util.stream.Collectors;

class NumberFromString {
  private final static String[] keyWords =
      {"number", "cipher", "numeration", "number", "digit", "symbol", "sum", "unit"};
  private final static String conjunction = "and";
  private final static long minimumMatchCount = 2;
  private final static WordClass numberClass = WordClass.NOUN;

  // TODO cache for not number words also?
  private final static Map<String, Long> CACHE = new HashMap<>();

  NumberFromString() {
    initCache();
  }

  private void initCache(){
    synchronized (CACHE) {
      for(Number number : Number.values()){
        CACHE.put(number.name().toLowerCase(), number.getValue());
      }
    }
  }

  List<Long> tryGetNumbersFromWords(Collection<String> words) {
    return tryGetNumbersFromLongList(getLongList(words));
  }

  Long tryGetNumberFromWord(String word) {
    return getFromCacheOrCompute(word);
  }

  private Long getFromCacheOrCompute(String word) {
    synchronized (CACHE) {
      return CACHE.computeIfAbsent(word, this::computeNumber);
    }
  }

  private List<Long> getLongList(Collection<String> words) {
    return words.stream()
        .filter(word -> !word.equals(conjunction))
        .map(NumberUtility::tryGetNumberFromWord)
        .collect(Collectors.toList());
  }

  private Long computeNumber(String word) {
    if (StringUtility.isNumeric(word)) {
      return tryParse(word);
    } else if (StringUtility.isAlphabetic(word)) {
      return tryGetNumberFromDictionary(word);
    }
    return null;
  }

  private Long tryParse(String string) {
    try {
      return Long.parseLong(string);
    } catch (NumberFormatException ex) {
      return null;
    }
  }

  private Long tryGetNumberFromDictionary(String word) {
    return tryGetFromCandidates(getCandidates(WordHelper.getByWordClass(word, numberClass)));
  }

  private ArrayList<StringCandidate> getCandidates(Collection<WordDao> words) {
    return words.stream()
        .map(WordDao::getDescription)
        .map(desc -> new StringCandidate(getKeyWordMatchCount(desc) + getContainsDigitMatch(desc), desc))
        .filter(candidate -> candidate.getMatchCount() >= minimumMatchCount)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  private Long tryGetFromCandidates(Collection<StringCandidate> candidates) {
    return candidates.stream()
        .map(StringCandidate::getContent)
        .map(StringUtility::getOnlyNumeric)
        .map(this::tryParse)
        .filter(Objects::nonNull)
        .findFirst().orElse(null);
  }

  private List<Long> tryGetNumbersFromLongList(List<Long> list) {
    return getGroupsFromLongList(list).stream()
        .filter(group -> !group.isEmpty())
        .map(this::getLongFromGroup)
        .collect(Collectors.toList());
  }

  private List<List<Long>> getGroupsFromLongList(List<Long> list) {
    List<List<Long>> groups = new ArrayList<>();
    List<Long> currentGroup = new ArrayList<>();

    for (Long number : list) {
      if (number == null) {
        if (!currentGroup.isEmpty()) {
          groups.add(new ArrayList<>(currentGroup));
          currentGroup.clear();
        }
      } else {
        currentGroup.add(number);
      }
    }

    groups.add(currentGroup);

    return groups;
  }

  private Long getLongFromGroup(List<Long> group){
    Collections.reverse(group);

    Long[] longs = group.toArray(new Long[group.size()]);
    Long result = 0L;
    Long lastPowerOf10 = 1L;

    for (Long current : longs) {
      if(isPowerOf10(current)){
        if(current < lastPowerOf10){
          lastPowerOf10 *= current;
        }else{
          lastPowerOf10 = current;
        }
      }else {
        result += current * lastPowerOf10;
      }
    }

    return isPowerOf10(longs[longs.length-1]) ? result + longs[longs.length-1] : result ;
  }

  private boolean isPowerOf10(Long number){
    return number >= 10 && String.valueOf(number).replaceAll("0", "").equals("1");
  }

  private int getContainsDigitMatch(String string) {
    return StringUtility.containsDigit(string) ? 1 : 0;
  }

  private long getKeyWordMatchCount(String string) {
    return Arrays.stream(keyWords).filter(word -> StringUtility.containsIgnoreCase(word, string)).count();
  }
}
