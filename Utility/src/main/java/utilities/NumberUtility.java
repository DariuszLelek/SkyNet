/*
 * Created by Dariusz Lelek on 9/17/17 4:31 PM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;


import utilities.number.NumberFromString;

import java.util.*;

public class NumberUtility {
  private static final NumberFromString numberFromString = new NumberFromString();

  public static float getRatioMinToMax(int first, int second) {
    int min = Math.min(first, second);
    int max = Math.max(first, second);
    return (float) min / max;
  }

  public static List<Long> tryGetNumbersFromWords(Collection<String> words) {
    return numberFromString.tryGetNumbersFromWords(words);
  }

  public static Long tryGetNumberFromWord(String word) {
    return numberFromString.tryGetNumberFromWord(word);
  }

}
