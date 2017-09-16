/*
 * Created by Dariusz Lelek on 9/13/17 9:15 PM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import dao.WordDao;
import entity.WordClass;
import helper.entity.WordHelper;
import process.candidate.StringCandidate;

import java.util.*;
import java.util.stream.Collectors;

public class NumberUtility {
  private final static String[] keyWords = {"number", "digit", "symbol", "sum", "unit", "object"};
  private final static long minimumMatchCount = 2;
  private final static WordClass numberClass = WordClass.NOUN;

  public static float getRatioMinToMax(int first, int second){
    int min = Math.min(first, second);
    int max = Math.max(first, second);
    return (float) min/max;
  }

  public static Integer tryGetNumberFromWords(Collection<String> words){
    List<Integer> numbers = new LinkedList<>();

    for(String word : words){
      Integer number = tryGetNumberFromWord(word);
      if(number != null){
        numbers.add(number);
      }
    }

   // numbers.

    // TODO
    return null;
  }

  public static Integer tryGetNumberFromWord(String word){
    Integer result = tryParse(word);
    return result != null ? result : tryGetNumberFromDictionary(word);
  }

  private static Integer tryGetNumberFromDictionary(String word){
    return tryGetFromCandidates(getCandidates(WordHelper.getByWordClass(word, numberClass)));
  }

  private static Integer tryGetFromCandidates(Collection<StringCandidate> candidates){
    return candidates.stream().filter(candidate -> candidate.getMatchCount() >= minimumMatchCount)
        .map(StringCandidate::getContent)
        .map(NumberUtility::tryParse)
        .filter(Objects::nonNull)
        .findFirst().orElse(null);
  }

  private static SortedSet<StringCandidate> getCandidates(Collection<WordDao> words){
    return words.stream()
        .map(WordDao::getDescription)
        .map(desc -> new StringCandidate(getKeyWordsCount(desc), desc))
        .collect(Collectors.toCollection(TreeSet::new));
  }

  private static long getKeyWordsCount(String string){
    return Arrays.stream(keyWords).filter(string::contains).count();
  }

  private static Integer tryParse(String string){
    try {
      return Integer.parseInt(StringUtility.getOnlyNumeric(string));
    }catch (NumberFormatException ex){
      return null;
    }
  }
}
