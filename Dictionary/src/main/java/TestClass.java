/*
 * Created by Dariusz Lelek on 9/10/17 9:54 PM
 * Copyright (c) 2017. All rights reserved.
 */

import dictionary.DictionaryFactory;
import config.DataBaseSchema;
import dictionary.word.Word;
import hibernate.HibernateUtilityFactory;

import java.util.Arrays;


public class TestClass {

  public static void main(String[] args) {
    Word w = new Word("verb", "verb", "v");

    DictionaryFactory.getWordPreserver().save(w);

    String[] words = {"none", "none2"};

    System.out.println(DictionaryFactory.getWordProvider().getWord("add").getWordClass());
    System.out.println(DictionaryFactory.getWordProvider().getWords(Arrays.asList(words)).size());

    HibernateUtilityFactory.getBySchema(DataBaseSchema.DICTIONARY).closeSessionFactory();
  }
}
