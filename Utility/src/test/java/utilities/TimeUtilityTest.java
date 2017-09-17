/*
 * Created by Dariusz Lelek on 9/17/17 4:31 PM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import constant.Time;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TimeUtilityTest {
  @Test
  public void getDate() throws Exception {
    logger.info("getTime - start");

    String text = "every friday ad five";

    TimeUtility.getDate(Arrays.asList(text.split(" ")));
  }

  private final static Logger logger = Logger.getLogger(TimeUtilityTest.class);

  private final String[] validTimeSingular = {"Month", "Year", "Day", "later", "monthly", "january"};
  private final String[] validTimePlural = {"months", "years", "days", "Seconds", "Hours"};
  private final String[] invalidTime = {"Web", "people", "Bad", "Going"};

  @Test
  public void getTime() throws Exception {
    logger.info("getTime - start");

    for(Time fromEnum : Time.values()){
      assertEquals(fromEnum.getDuration(), TimeUtility.getTime(fromEnum.name()).getDuration());
    }

    assertEquals(Long.MIN_VALUE, TimeUtility.getTime(invalidTime[0]).getDuration());
    assertEquals(0, TimeUtility.getTime("Now").getDuration());
    assertEquals(60, TimeUtility.getTime("minutes").getDuration());
    assertEquals(3600, TimeUtility.getTime("HOURS").getDuration());

    assertEquals(Time.UNKNOWN, TimeUtility.getTime("sdasads"));
    assertTrue(TimeUtility.getTime("unknown").getDuration() < 0);
  }

  @Test
  public void isTime() throws Exception {
    logger.info("isTime - start");

    for(Time fromEnum : Time.values()){
      assertTrue(TimeUtility.isTime(fromEnum.name()));
    }

    for(String singular : validTimeSingular){
      assertTrue(TimeUtility.isTime(singular));
    }

    for(String plural : validTimePlural){
      assertTrue(TimeUtility.isTime(plural));
    }

    for(String invalid : invalidTime){
      assertFalse(TimeUtility.isTime(invalid));
    }
  }


  @Test
  public void getTimeIsRepeatable() throws Exception {
    logger.info("getTimeIsRepeatable - start");

    assertTrue(TimeUtility.getTime("weekly").isRepeatable());
    assertTrue(TimeUtility.getTime("MONTHLY").isRepeatable());
    assertFalse(TimeUtility.getTime("Minute").isRepeatable());
    assertFalse(TimeUtility.getTime("january").isRepeatable());
  }
}