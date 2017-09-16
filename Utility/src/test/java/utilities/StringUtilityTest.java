/*
 * Created by Dariusz Lelek on 9/16/17 1:08 AM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StringUtilityTest {
  @Test
  public void getNotNull() throws Exception {
    assertEquals("", StringUtility.getNotNull(null));
    assertEquals("", StringUtility.getNotNull(""));
    assertEquals("ABC", StringUtility.getNotNull("ABC"));
  }

  @Test
  public void containsStringIgnoreCase() throws Exception {
    List<String> list = new ArrayList<>();
    list.add("abc");
    list.add("ABC");

    assertTrue(StringUtility.containsStringIgnoreCase(list, "aBC"));
    assertTrue(StringUtility.containsStringIgnoreCase(list, "abc"));
    assertTrue(StringUtility.containsStringIgnoreCase(list, "ABC"));

    assertFalse(StringUtility.containsStringIgnoreCase(list, "abb"));
    assertFalse(StringUtility.containsStringIgnoreCase(list, ""));
  }

  @Test
  public void firstInSecondPercent() throws Exception {
    final float delta = 0.01F;

    assertEquals(0, StringUtility.firstInSecondPercent("", "AbC"), delta);
    assertEquals(0, StringUtility.firstInSecondPercent("", ""), delta);
    assertEquals(0, StringUtility.firstInSecondPercent("", null), delta);
    assertEquals(0, StringUtility.firstInSecondPercent(null, ""), delta);
    assertEquals(0, StringUtility.firstInSecondPercent(null, null), delta);

    assertEquals(1F, StringUtility.firstInSecondPercent("aBC", "AbC"), delta);
    assertEquals(1F, StringUtility.firstInSecondPercent("aB", "AbC"), delta);
    assertEquals(1F, StringUtility.firstInSecondPercent("A", "AbC"), delta);

    assertEquals(1/3F, StringUtility.firstInSecondPercent("AcB", "AbC"), delta);
    assertEquals(2/3F, StringUtility.firstInSecondPercent("aBd", "AbC"), delta);
  }

  @Test
  public void isAlphabetic() throws Exception {
    assertTrue(StringUtility.isAlphabetic("abcDEF"));
    assertTrue(StringUtility.isAlphabetic("o"));

    assertFalse(StringUtility.isAlphabetic(null));
    assertFalse(StringUtility.isAlphabetic(""));
    assertFalse(StringUtility.isAlphabetic("123"));
    assertFalse(StringUtility.isAlphabetic("abc1"));
  }

}