/*
 * Created by Dariusz Lelek on 9/10/17 9:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class WordTest {
  private Word word;

  @Before
  public void setUp() throws Exception {
    word = new Word();
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void getWordClass() throws Exception {
    System.out.println("getWordClass");

    word.setWordClassString(WordClass.NOUN.toString());
    assertEquals(WordClass.NOUN, word.getWordClass());
    word.setWordClassString("noun");
    assertEquals(WordClass.NOUN, word.getWordClass());
    word.setWordClassString("Noun");
    assertEquals(WordClass.NOUN, word.getWordClass());
    word.setWordClassString("some unknown class");
    assertEquals(WordClass.UNKNOWN, word.getWordClass());
  }

  @Test
  public void getSynonymsList() throws Exception {
    System.out.println("getWordClass");

    String[] synonyms = {"string1", "string2", "string3"};
    String synonymsString = String.join(",", synonyms);

    word.setSynonyms(synonymsString);
    assertEquals(synonyms.length, word.getSynonymsList().size());
  }
}