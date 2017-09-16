/*
 * Created by Dariusz Lelek on 9/16/17 1:08 AM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StringUtilitiesTest {
  @Test
  public void getNotNull() throws Exception {
    assertEquals("", StringUtilities.getNotNull(null));
    assertEquals("", StringUtilities.getNotNull(""));
    assertEquals("ABC", StringUtilities.getNotNull("ABC"));
  }

  @Test
  public void containsStringIgnoreCase() throws Exception {
    List<String> list = new ArrayList<>();
    list.add("abc");
    list.add("ABC");

    assertTrue(StringUtilities.containsStringIgnoreCase(list, "aBC"));
    assertTrue(StringUtilities.containsStringIgnoreCase(list, "abc"));
    assertTrue(StringUtilities.containsStringIgnoreCase(list, "ABC"));

    assertFalse(StringUtilities.containsStringIgnoreCase(list, "abb"));
    assertFalse(StringUtilities.containsStringIgnoreCase(list, ""));
  }

  @Test
  public void firstInSecondPercent() throws Exception {
    final float delta = 0.01F;

    assertEquals(0, StringUtilities.firstInSecondPercent("", "AbC"), delta);
    assertEquals(0, StringUtilities.firstInSecondPercent("", ""), delta);
    assertEquals(0, StringUtilities.firstInSecondPercent("", null), delta);
    assertEquals(0, StringUtilities.firstInSecondPercent(null, ""), delta);
    assertEquals(0, StringUtilities.firstInSecondPercent(null, null), delta);

    assertEquals(1F, StringUtilities.firstInSecondPercent("aBC", "AbC"), delta);
    assertEquals(1F, StringUtilities.firstInSecondPercent("aB", "AbC"), delta);
    assertEquals(1F, StringUtilities.firstInSecondPercent("A", "AbC"), delta);

    assertEquals(1/3F, StringUtilities.firstInSecondPercent("AcB", "AbC"), delta);
    assertEquals(2/3F, StringUtilities.firstInSecondPercent("aBd", "AbC"), delta);
  }

  @Test
  public void isAlphabetic() throws Exception {
    assertTrue(StringUtilities.isAlphabetic("abcDEF"));
    assertTrue(StringUtilities.isAlphabetic("o"));

    assertFalse(StringUtilities.isAlphabetic(null));
    assertFalse(StringUtilities.isAlphabetic(""));
    assertFalse(StringUtilities.isAlphabetic("123"));
    assertFalse(StringUtilities.isAlphabetic("abc1"));
  }

}