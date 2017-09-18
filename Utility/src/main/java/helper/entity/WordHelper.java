/*
 * Created by Dariusz Lelek on 9/16/17 5:17 PM
 * Copyright (c) 2017. All rights reserved.
 */

package helper.entity;

import dao.WordDao;
import constant.WordClass;
import exception.NotValidWordException;
import hibernate.provider.DaoProviderFactory;
import utilities.StringUtility;

import java.util.Collection;
import java.util.stream.Collectors;

public class WordHelper {
  public static Collection<WordDao> getByWordClass(String word, WordClass wordClass){
    return DaoProviderFactory.getWordProvider().getByKey(WordDao.WORD, word)
        .stream().filter(w -> wordClass == w.getWordClass())
        .collect(Collectors.toList());
  }

  public static boolean isValidWord(String string) throws NotValidWordException {
    if(StringUtility.isAlphabetic(string)){
      return true;
    }else{
      throw new NotValidWordException();
    }
  }
}
