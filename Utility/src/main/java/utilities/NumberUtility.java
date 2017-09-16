/*
 * Created by Dariusz Lelek on 9/13/17 9:15 PM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import dao.WordDao;
import entity.WordClass;
import helper.entity.WordHelper;
import org.apache.log4j.Logger;
import process.candidate.StringCandidate;

import java.util.*;
import java.util.stream.Collectors;

public class NumberUtility {
  private final static String[] keyWords =
      {"number", "cipher", "numeration", "number", "digit", "symbol", "sum", "unit"};
  private final static String conjunction = "and";
  private final static long minimumMatchCount = 2;
  private final static WordClass numberClass = WordClass.NOUN;
  private final static Map<String, Long> CACHE = new HashMap<>();

  public static float getRatioMinToMax(int first, int second) {
    int min = Math.min(first, second);
    int max = Math.max(first, second);
    return (float) min / max;
  }

  public static List<Long> tryGetNumbersFromWords(Collection<String> words) {
    return tryGetNumbersFromLongList(getLongList(words));
  }

  public static Long tryGetNumberFromWord(String word) {
    return getFromCacheOrCompute(word);
  }

  private static Long getFromCacheOrCompute(String word) {
    synchronized (CACHE) {
      return CACHE.computeIfAbsent(word, NumberUtility::computeNumber);
    }
  }

  private static List<Long> getLongList(Collection<String> words) {
    return words.stream()
        .filter(word -> !word.equals(conjunction))
        .map(NumberUtility::tryGetNumberFromWord)
        .collect(Collectors.toList());
  }

  private static Long computeNumber(String word) {
    if (StringUtility.isNumeric(word)) {
      return tryParse(word);
    } else if (StringUtility.isAlphabetic(word)) {
      return tryGetNumberFromDictionary(word);
    }
    return null;
  }

  private static Long tryParse(String string) {
    try {
      return Long.parseLong(string);
    } catch (NumberFormatException ex) {
      return null;
    }
  }

  private static Long tryGetNumberFromDictionary(String word) {
    return tryGetFromCandidates(getCandidates(WordHelper.getByWordClass(word, numberClass)));
  }

  private static ArrayList<StringCandidate> getCandidates(Collection<WordDao> words) {
    return words.stream()
        .map(WordDao::getDescription)
        .map(desc -> new StringCandidate(getKeyWordMatchCount(desc) + getContainsDigitMatch(desc), desc))
        .filter(candidate -> candidate.getMatchCount() >= minimumMatchCount)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  private static Long tryGetFromCandidates(Collection<StringCandidate> candidates) {
    return candidates.stream()
        .map(StringCandidate::getContent)
        .map(StringUtility::getOnlyNumeric)
        .map(NumberUtility::tryParse)
        .filter(Objects::nonNull)
        .findFirst().orElse(null);
  }

  private static List<Long> tryGetNumbersFromLongList(List<Long> list) {
    return getNumberGroupsFromLongList(list).stream()
        .filter(group -> !group.isEmpty())
        .map(NumberUtility::getNumberFromGroup)
        .collect(Collectors.toList());
  }

  private static Long getNumberFromGroup(List<Long> group) {
    return getSubGroupsFromGroup(group).stream()
        .filter(subGroup -> !subGroup.isEmpty())
        .map(NumberUtility::getNumberByMultiplyingElements)
        .mapToLong(Long::longValue)
        .sum();
  }

  private static Long getNumberByMultiplyingElements(List<Long> subGroup) {
    return subGroup.stream().reduce(1L, (a, b) -> a * b);
  }

  // TODO rewrite to streams...
  private static List<List<Long>> getSubGroupsFromGroup(List<Long> group) {
    List<List<Long>> subGroups = new ArrayList<>();
    List<Long> currentGroup = new ArrayList<>();

    for (Long number : group) {
      if (!currentGroup.isEmpty() && number <= currentGroup.get(currentGroup.size() - 1)) {
        subGroups.add(new ArrayList<>(currentGroup));
        currentGroup.clear();
      }
      currentGroup.add(number);
    }

    subGroups.add(currentGroup);

    return subGroups;
  }

  // TODO rewrite to streams...
  private static List<List<Long>> getNumberGroupsFromLongList(List<Long> list) {
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

  private static int getContainsDigitMatch(String string) {
    return StringUtility.containsDigit(string) ? 1 : 0;
  }

  private static long getKeyWordMatchCount(String string) {
    return Arrays.stream(keyWords).filter(word -> StringUtility.containsIgnoreCase(word, string)).count();
  }
}
