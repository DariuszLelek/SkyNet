/*
 * Created by Dariusz Lelek on 9/16/17 1:22 AM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NumberUtilityTest {
  @Test
  public void tryGetNumber() throws Exception {
    assertEquals((Integer) 5, NumberUtility.tryGetNumberFromWord("5"));
    assertEquals((Integer) 1000000, NumberUtility.tryGetNumberFromWord("1000000"));
    assertEquals((Integer) 20, NumberUtility.tryGetNumberFromWord("twenty"));

    List<String> words = new ArrayList<String>(){{
      add("one");
      add("hundred");
      add("and");
      add("twenty");
      add("seven");
    }};

    assertEquals((Long) 127L, NumberUtility.tryGetNumberFromWords(words));
    //assertNull(NumberUtility.tryGetNumberFromWord("a5"));
  }

  @Test
  public void getRatioMinToMax() throws Exception {
    float delta = 0.01F;
    assertEquals(1/3F, NumberUtility.getRatioMinToMax(1,3), delta);
    assertEquals(1/3F, NumberUtility.getRatioMinToMax(3,1), delta);
    assertEquals(1F, NumberUtility.getRatioMinToMax(3,3), delta);
    assertEquals(0, NumberUtility.getRatioMinToMax(0,3), delta);
  }

}