/*
 * Created by Dariusz Lelek on 9/16/17 1:31 AM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.builder;

import dao.WordDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordDaoBuilderTest {
  @Test
  public void build() throws Exception {
    WordDao
        wordDao1 = new WordDaoBuilder(new String[]{"valid", "n.", null}).build(),
        wordDao2 = new WordDaoBuilder(new String[]{"invalid1", null, null}).build(),
        wordDao3 = new WordDaoBuilder(new String[]{"valid"}).build();

    assertEquals("valid", wordDao1.getWord());
    assertEquals("noun", wordDao1.getWordClassString());
    assertEquals("", wordDao1.getDescription());

    assertEquals("", wordDao2.getWord());
    assertEquals("", wordDao3.getWord());
  }
}