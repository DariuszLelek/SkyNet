/*
 * Created by Dariusz Lelek on 9/10/17 9:54 PM
 * Copyright (c) 2017. All rights reserved.
 */

import dao.entity.PersonDAO;
import dictionary.DictionaryFactory;
import config.DataBaseConfig;
import dictionary.WordClass;
import dictionary.word.Word;
import hibernate.HibernateUtilityFactory;
import hibernate.preserver.DataPreserver;


public class TestClass {

  public static void main(String[] args) {

    DictionaryFactory.getWordPreserver().save(new Word("send", WordClass.NOUN).getDAO());
    DictionaryFactory.getWordPreserver().save(new Word("email", WordClass.VERB).getDAO());
    DictionaryFactory.getWordPreserver().save(new Word("daro", WordClass.NOUN).getDAO());
//
//    PersonDAO p = new PersonDAO();
//
//    p.setEmail("dariusz.lelek@gmail.com");
//    p.setFirstName("dariusz");
//    p.setLastName("lelek");
//
//    DataPreserver dp = new DataPreserver(DataBaseConfig.PROD);
//
//    dp.save(p);
//

    System.out.println(DictionaryFactory.getWordProvider().getWord("email").getWordClass());

    HibernateUtilityFactory.closeAllSessionFactories();
  }
}
