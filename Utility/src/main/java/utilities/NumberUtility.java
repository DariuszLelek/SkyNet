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
  private static final Logger logger = Logger.getLogger(NumberUtility.class);

  private final static String[] keyWords =
      {"number", "cipher", "numeration", "number", "digit", "symbol", "sum", "unit"};
  private final static String conjunction = "and";
  private final static long minimumMatchCount = 2;
  private final static WordClass numberClass = WordClass.NOUN;

  private final static Map<String, Long> CACHE = new HashMap<>();

  public static float getRatioMinToMax(int first, int second){
    int min = Math.min(first, second);
    int max = Math.max(first, second);
    return (float) min/max;
  }

  public static List<Long> tryGetNumbersFromWords(Collection<String> words){
    List<Long> numbers = words.stream().filter(word -> !word.equals(conjunction))
        .map(NumberUtility::tryGetNumberFromWord)
        .collect(Collectors.toList());
    return tryGetNumbersFromIntegerList(numbers);
  }

  private static List<Long> tryGetNumbersFromIntegerList(List<Long> integers){
    return getNumberGroupsFromIntegerList(integers).stream()
        .filter(list -> !list.isEmpty())
        .map(NumberUtility::getNumberFromGroup)
        .collect(Collectors.toList());
  }

  private static Long getNumberFromGroup(List<Long> group){
    return getSubGroupsFromGroup(group).stream()
        .filter(list -> !list.isEmpty())
        .map(NumberUtility::getNumberByMultiplyingElements)
        .mapToLong(Long::longValue)
        .sum();
  }

  private static Long getNumberByMultiplyingElements(List<Long> list){
    Long result = 1L;

    for(Long i : list){
      result *= i;
    }

    return result;
  }

  // TODO rewrite to streams...
  private static List<List<Long>> getSubGroupsFromGroup(List<Long> group){
    List<List<Long>> subGroups = new ArrayList<>();
    List<Long> currentGroup = new ArrayList<>();

    for(Long i : group){
      if (!currentGroup.isEmpty() && i <= currentGroup.get(currentGroup.size() - 1)) {
        subGroups.add(new ArrayList<>(currentGroup));
        currentGroup.clear();
      }
      currentGroup.add(i);
    }

    subGroups.add(currentGroup);

    return subGroups;
  }

  // TODO rewrite to streams...
  private static List<List<Long>> getNumberGroupsFromIntegerList(List<Long> list){
    List<List<Long>> groups = new ArrayList<>();
    List<Long> currentGroup = new ArrayList<>();

    for(Long i : list){
      if(i == null){
        if(!currentGroup.isEmpty()){
          groups.add(new ArrayList<>(currentGroup));
          currentGroup.clear();
        }
      }else{
        currentGroup.add(i);
      }
    }

    groups.add(currentGroup);

    return groups;
  }

  private static Long getFromCacheOrCompute(String word){
    synchronized (CACHE){
      return CACHE.computeIfAbsent(word, NumberUtility::computeNumber);
    }
  }

  private static Long computeNumber(String word){
    if(StringUtility.isNumeric(word)){
      return tryParse(word);
    }else if (StringUtility.isAlphabetic(word)){
      return tryGetNumberFromDictionary(word);
    }
    return null;
  }

  public static Long tryGetNumberFromWord(String word){
    return getFromCacheOrCompute(word);
  }

  private static Long tryGetNumberFromDictionary(String word){
    return tryGetFromCandidates(getCandidates(WordHelper.getByWordClass(word, numberClass)));
  }

  private static Long tryGetFromCandidates(Collection<StringCandidate> candidates){
    return candidates.stream()
        .filter(candidate -> candidate.getMatchCount() >= minimumMatchCount)
        .map(StringCandidate::getContent)
        .map(StringUtility::getOnlyNumeric)
        .map(NumberUtility::tryParse)
        .filter(Objects::nonNull)
        .findFirst().orElse(null);
  }

  private static ArrayList<StringCandidate> getCandidates(Collection<WordDao> words){
    return words.stream()
        .map(WordDao::getDescription)
        .map(desc -> new StringCandidate(getKeyWordMatchCount(desc) + getContainsDigitMatch(desc), desc))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  private static int getContainsDigitMatch(String string){
    return StringUtility.containsDigit(string) ? 1 : 0;
  }

  private static long getKeyWordMatchCount(String string){
    return Arrays.stream(keyWords).filter(word ->StringUtility.containsIgnoreCase(word, string)).count();
  }

  private static Long tryParse(String string){
    try {
      return Long.parseLong(string);
    }catch (NumberFormatException ex){
      return null;
    }
  }
}
