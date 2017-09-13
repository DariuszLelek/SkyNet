/*
 * Created by Dariusz Lelek on 9/10/17 9:54 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dictionary;


import dictionary.access.WordPreserver;
import dictionary.access.WordProvider;

public class  DictionaryFactory {
  private final static WordProvider wordProvider = new WordProvider();
  private final static WordPreserver wordPreserver = new WordPreserver();

  public static WordProvider getWordProvider(){
    return wordProvider;
  }
  public static WordPreserver getWordPreserver(){
    return wordPreserver;
  }
}
