/*
 * Created by Dariusz Lelek on 9/17/17 4:31 PM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import constant.TimeUnit;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TimeUnitUtilityTest {
  @Test
  public void getDate() throws Exception {
    logger.info("getTimeUnit - start");

    String text = "tomorrow at 7 30";

    TimeUtility.getDateTime(Arrays.asList(text.split(" ")));
  }

  private final static Logger logger = Logger.getLogger(TimeUnitUtilityTest.class);

  private final String[] validTimeSingular = {"Month", "Year", "Day", "later", "monthly", "january"};
  private final String[] validTimePlural = {"months", "years", "days", "Seconds", "Hours"};
  private final String[] invalidTime = {"Web", "people", "Bad", "Going"};

  @Test
  public void getTime() throws Exception {
    logger.info("getTimeUnit - start");

    for(TimeUnit fromEnum : TimeUnit.values()){
      assertEquals(fromEnum.getDuration(), TimeUtility.getTimeUnit(fromEnum.name()).getDuration());
    }

    assertEquals(Long.MIN_VALUE, TimeUtility.getTimeUnit(invalidTime[0]).getDuration());
    assertEquals(0, TimeUtility.getTimeUnit("Now").getDuration());
    assertEquals(60, TimeUtility.getTimeUnit("minutes").getDuration());
    assertEquals(3600, TimeUtility.getTimeUnit("HOURS").getDuration());

    assertEquals(TimeUnit.UNKNOWN, TimeUtility.getTimeUnit("sdasads"));
    assertTrue(TimeUtility.getTimeUnit("unknown").getDuration() < 0);
  }

  @Test
  public void isTime() throws Exception {
    logger.info("isTimeUnit - start");

    for(TimeUnit fromEnum : TimeUnit.values()){
      assertTrue(TimeUtility.isTimeUnit(fromEnum.name()));
    }

    for(String singular : validTimeSingular){
      assertTrue(TimeUtility.isTimeUnit(singular));
    }

    for(String plural : validTimePlural){
      assertTrue(TimeUtility.isTimeUnit(plural));
    }

    for(String invalid : invalidTime){
      assertFalse(TimeUtility.isTimeUnit(invalid));
    }
  }


  @Test
  public void getTimeIsRepeatable() throws Exception {
    logger.info("getTimeIsRepeatable - start");

    assertTrue(TimeUtility.getTimeUnit("weekly").isRepeatable());
    assertTrue(TimeUtility.getTimeUnit("MONTHLY").isRepeatable());
    assertFalse(TimeUtility.getTimeUnit("Minute").isRepeatable());
    assertFalse(TimeUtility.getTimeUnit("january").isRepeatable());
  }
}