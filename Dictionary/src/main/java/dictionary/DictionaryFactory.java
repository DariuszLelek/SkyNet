/*
 * Created by Dariusz Lelek on 9/10/17 9:54 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dictionary;

import dictionary.word.WordProvider;

public class  DictionaryFactory {
  private final static WordProvider wordProvider = new WordProvider();

  private final static DictionaryProvider dictionaryProvider = new DictionaryProvider();
  private final static DictionaryPreserver dictionaryPreserver = new DictionaryPreserver();

  public static WordProvider getWordProvider(){
    return wordProvider;
  }

  public static DictionaryProvider getDictionaryProvider() {
    return dictionaryProvider;
  }

  public static DictionaryPreserver getDictionaryPreserver() {
    return dictionaryPreserver;
  }
}
