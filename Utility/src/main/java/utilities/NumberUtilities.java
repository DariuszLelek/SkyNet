/*
 * Created by Dariusz Lelek on 9/13/17 9:15 PM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

public class NumberUtilities {
  public static float getRatioMinToMax(int first, int second){
    int min = Math.min(first, second);
    int max = Math.max(first, second);
    return (float) min/max;
  }
}
