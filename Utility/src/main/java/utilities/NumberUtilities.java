/*
 * Created by Dariusz Lelek on 9/13/17 9:15 PM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

public class NumberUtilities {
  public static double getRatioMinToMax(int first, int second){
    int min = Math.min(first, second);
    int max = Math.max(first, second);
    return (double) min/max;
  }

  public static void main(String[] args) {
    System.out.println(getRatioMinToMax(5,3));
  }
}
