/*
 * Created by Dariusz Lelek on 9/18/17 10:52 PM
 * Copyright (c) 2017. All rights reserved.
 */

package text;

import org.apache.log4j.Logger;
import org.junit.Test;
import text.sentence.Sentence;
import utilities.StringUtilityTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class SentenceAnalyzerTest {

  private final static Logger logger = Logger.getLogger(SentenceAnalyzerTest.class);

  @Test
  public void getSentence() throws Exception {
    logger.info("getSentence - start");

    assertNotNull(SentenceAnalyzer.getSentence(Arrays.asList("".split(""))));
    assertNotNull(SentenceAnalyzer.getSentence("".split("")));
    assertNotNull(SentenceAnalyzer.getSentence((Collection<String>) null));
    assertNotNull(SentenceAnalyzer.getSentence((String[]) null));

    assertTrue(SentenceAnalyzer.getSentence(Arrays.asList("".split(""))).isEmpty());
  }

  @Test
  public void sentenceHasNumber() throws Exception {
    logger.info("sentenceHasNumber - start");

    assertTrue(SentenceAnalyzer.getSentence("sentence with number five".split("")).hasNumber());
    assertTrue(SentenceAnalyzer.getSentence("one hundred, some text".split("")).hasNumber());
  }
}