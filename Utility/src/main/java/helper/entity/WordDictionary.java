/*
 * Created by Dariusz Lelek on 9/16/17 5:17 PM
 * Copyright (c) 2017. All rights reserved.
 */

package helper.entity;

import dao.WordDao;
import constant.WordClass;
import hibernate.provider.DaoProviderFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WordDictionary {

  private static final Map<String, Collection<WordDao>> cachedWords = new HashMap<>();

  public static Collection<WordDao> getByString(String string){
    return getFromCache(string);
  }

  public static Collection<WordDao> getByWordClass(String string, WordClass wordClass){
    return getFromCache(string).stream()
        .filter(w -> wordClass == w.getWordClass())
        .collect(Collectors.toList());
  }

  private static Collection<WordDao> getFromCache(String string){
    synchronized (cachedWords){
      return cachedWords.computeIfAbsent(string, WordDictionary::getFromProvider);
    }
  }

  private static Collection<WordDao> getFromProvider(String string){
    return DaoProviderFactory.getWordProvider().getByKey(WordDao.WORD, string);
  }

}
