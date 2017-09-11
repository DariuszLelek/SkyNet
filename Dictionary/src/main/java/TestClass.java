/*
 * Created by Dariusz Lelek on 9/10/17 9:54 PM
 * Copyright (c) 2017. All rights reserved.
 */

import dictionary.DictionaryFactory;
import config.DataBaseSchema;
import dictionary.Word;
import hibernate.HibernateUtilityFactory;


public class TestClass {

  public static void main(String[] args) {
    Word w = new Word("remove", "noun", "del");

    //DictionaryFactory.getDictionaryPreserver().saveEntity(w);

    String[] words = {"none", "none2"};

    System.out.println(DictionaryFactory.getWordProvider().getWord("add").getWordClass());
    System.out.println(DictionaryFactory.getWordProvider().getWords(words).size());


    HibernateUtilityFactory.getBySchema(DataBaseSchema.DICTIONARY).closeSessionFactory();
  }
}
