/*
 * Created by Dariusz Lelek on 9/13/17 9:12 PM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import java.util.Collection;

public class StringUtilities {
  public static String getNotNull(String string){
    return string!= null ? string : "";
  }

  public static boolean containsStringIgnoreCase(Collection<String> collection, String string){
    return collection.stream().anyMatch(string::equalsIgnoreCase);
  }

  public static float firstInSecondPercent(String first, String second){
    float result = 0F;

    first = getNotNull(first);
    second = getNotNull(second);

    float diff = 1F / (first.length() > 0 ? first.length() : 1);

    int idx = 0;
    for (char c : first.toCharArray()) {
      if (second.length() > idx) {
        if (Character.toLowerCase(second.charAt(idx)) == Character.toLowerCase(c)) {
          result += diff;
        } else {
          break;
        }
        idx++;
      } else {
        break;
      }
    }

    return result > 1F ? 1F : result;
  }

  public static boolean isAlphabetic(String name) {
    char[] chars = getNotNull(name).toCharArray();

    for (char c : chars) {
      if(!Character.isLetter(c)) {
        return false;
      }
    }

    return chars.length > 0;
  }

}
