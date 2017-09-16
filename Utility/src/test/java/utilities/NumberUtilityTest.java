/*
 * Created by Dariusz Lelek on 9/16/17 1:22 AM
 * Copyright (c) 2017. All rights reserved.
 */

package utilities;

import config.DataBaseConfig;
import hibernate.HibernateUtility;
import hibernate.HibernateUtilityFactory;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.processing.Processor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NumberUtilityTest {
  private final static Logger logger = Logger.getLogger(NumberUtilityTest.class);

  @Before
  public void setUp() throws Exception {
    // TODO just to initialize Hibernate before tests start
    HibernateUtilityFactory.getByDatabaseConfig(DataBaseConfig.PROD);
  }

  @Test
  public void tryGetNumbersFromWords() throws Exception {
    logger.info("tryGetNumbersFromWords - start");

    String instruction1 = "one hundred and twenty five then another number is five million and one";
    String instruction2 = "one million and two hundred thousand and seven hundred and eleven";
    String instruction3 = "one thousand and two hundred";
    String instruction4 = "five million and two hundred thousand and then five thousand and two hundred and eleven";

    List<Long> result1 = NumberUtility.tryGetNumbersFromWords(Arrays.asList(instruction1.split(" ")));
    List<Long> result2 = NumberUtility.tryGetNumbersFromWords(Arrays.asList(instruction2.split(" ")));
    List<Long> result3 = NumberUtility.tryGetNumbersFromWords(Arrays.asList(instruction3.split(" ")));
    List<Long> result4 = NumberUtility.tryGetNumbersFromWords(Arrays.asList(instruction4.split(" ")));

    assertEquals((Long) 125L, result1.get(0));
    assertEquals((Long) 5000001L, result1.get(1));

    assertEquals((Long) 1200711L, result2.get(0));

    assertEquals((Long) 1200L, result3.get(0));

    assertEquals((Long) 5200000L, result4.get(0));
    assertEquals((Long) 5211L, result4.get(1));
  }

  @Test
  public void tryGetNumberFromWord() throws Exception {
    logger.info("tryGetNumberFromWord - start");

    assertEquals((Long) 5L, NumberUtility.tryGetNumberFromWord("5"));
    assertEquals((Long) 1000000L, NumberUtility.tryGetNumberFromWord("1000000"));
    assertEquals((Long) 123456789L, NumberUtility.tryGetNumberFromWord("123456789"));

    assertEquals((Long) 1L, NumberUtility.tryGetNumberFromWord("one"));
    assertEquals((Long) 11L, NumberUtility.tryGetNumberFromWord("eleven"));
    assertEquals((Long) 30L, NumberUtility.tryGetNumberFromWord("thirty"));
    assertEquals((Long) 100L, NumberUtility.tryGetNumberFromWord("hundred"));
    assertEquals((Long) 1000000L, NumberUtility.tryGetNumberFromWord("million"));
    assertEquals((Long) 1000000000L, NumberUtility.tryGetNumberFromWord("billion"));
    assertEquals((Long) 0L, NumberUtility.tryGetNumberFromWord("cipher"));
  }

  @Test
  public void getRatioMinToMax() throws Exception {
    logger.info("getRatioMinToMax - start");

    float delta = 0.01F;
    assertEquals(1/3F, NumberUtility.getRatioMinToMax(1,3), delta);
    assertEquals(1/3F, NumberUtility.getRatioMinToMax(3,1), delta);
    assertEquals(1F, NumberUtility.getRatioMinToMax(3,3), delta);
    assertEquals(0, NumberUtility.getRatioMinToMax(0,3), delta);
  }

}