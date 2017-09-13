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

    DictionaryFactory.getWordPreserver().save(new Word("send", "verb", "v"));
    DictionaryFactory.getWordPreserver().save(new Word("email", "noun", "v"));
    DictionaryFactory.getWordPreserver().save(new Word("daro", "noun", "v"));


    System.out.println(DictionaryFactory.getWordProvider().getWord("email").getWordClass());

    HibernateUtilityFactory.getBySchema(DataBaseSchema.DICTIONARY).closeSessionFactory();
  }
}
