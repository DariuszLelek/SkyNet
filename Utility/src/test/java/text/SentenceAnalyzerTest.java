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

    assertNotNull(SentenceAnalyzer.getSentence("".split("")));
    assertNotNull(SentenceAnalyzer.getSentence("".split("")));
    assertNotNull(SentenceAnalyzer.getSentence((String[]) null));

    assertTrue(SentenceAnalyzer.getSentence("".split("")).isEmpty());
  }

  @Test
  public void sentenceHasNumber() throws Exception {
    logger.info("sentenceHasNumber - start");

    Sentence s = SentenceAnalyzer.getSentence("one hundred some text".split(" "));

    assertTrue(SentenceAnalyzer.getSentence("sentence with number seven thousand".split(" ")).hasNumber());
    assertTrue(SentenceAnalyzer.getSentence("one hundred some text".split(" ")).hasNumber());
    assertTrue(SentenceAnalyzer.getSentence("this is number 67".split(" ")).hasNumber());
  }
}