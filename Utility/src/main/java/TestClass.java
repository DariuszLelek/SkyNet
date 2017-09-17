/*
 * Created by Dariusz Lelek on 9/16/17 4:47 PM
 * Copyright (c) 2017. All rights reserved.
 */

import constant.TimeUnit;
import dao.WordDao;
import hibernate.provider.DaoProviderFactory;

import java.util.Collection;

public class TestClass {
  public static void main(String[] args) {
    Collection<WordDao> fiveWord = DaoProviderFactory.getWordProvider().getByKey("word", "five");

    System.out.println(TimeUnit.MINUTE);

  }
}
