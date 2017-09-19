/*
 * Created by Dariusz Lelek on 9/17/17 4:31 PM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import java.util.*;

public class NumberUtility {
  private static final NumberFromString numberFromString = new NumberFromString();

  public static float getRatioMinToMax(int first, int second) {
    int min = Math.min(first, second);
    int max = Math.max(first, second);
    return (float) min / max;
  }

  // TODO fix implementation for "five five five five" example -> 20 instead o f 5555
  /**
   * @param words Collection of single words
   * @return List of Numbers in Long format in order they appear in <code>words</code>
   */
  public static List<Long> tryGetNumbersFromWords(Collection<String> words) {
    return numberFromString.tryGetNumbersFromWords(words);
  }

  /**
   * @param word single word
   * @return Number of Long type if <code>word</code> is a Number.
   */
  public static Long tryGetNumberFromWord(String word) {
    return numberFromString.tryGetNumberFromWord(word);
  }

}
