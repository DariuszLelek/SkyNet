/*
 * Created by Dariusz Lelek on 9/16/17 1:22 AM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumberUtilitiesTest {
  @Test
  public void getRatioMinToMax() throws Exception {
    float delta = 0.01F;
    assertEquals(1/3F, NumberUtilities.getRatioMinToMax(1,3), delta);
    assertEquals(1/3F, NumberUtilities.getRatioMinToMax(3,1), delta);
    assertEquals(1F, NumberUtilities.getRatioMinToMax(3,3), delta);
    assertEquals(0, NumberUtilities.getRatioMinToMax(0,3), delta);
  }

}