/*
 * Created by Dariusz Lelek on 9/13/17 9:12 PM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import java.util.Collection;

public class StringUtilities {
  public static boolean matchingWithProbability(String first, String second, double probability){
    boolean result = false;
    return false;
  }

  public static boolean containsStringIgnoreCase(Collection<String> collection, String string){
    return collection.stream().anyMatch(string::equalsIgnoreCase);
  }

  public static float firstInSecondPercent(String first, String second){
    float result = 0F;

    if(first != null){
      float diff = 1F / (first.length() > 0 ? first.length() : 1);

      int idx = 0;
      for(char c : first.toCharArray()) {
        if (second != null && second.length() > idx) {
          if (Character.toLowerCase(second.charAt(idx)) == Character.toLowerCase(c)) {
            result += diff;
          }
          idx++;
        } else {
          break;
        }
      }

      result = result > 1F ? 1F : result;
    }
    return result;
  }

}
