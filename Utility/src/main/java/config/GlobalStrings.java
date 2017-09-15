/*
 * Created by Dariusz Lelek on 9/10/17 9:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

package config;

import load.DictionaryDataBaseLoader;

public enum GlobalStrings {
  SPLITTER_SYNONYM(","),
  SPLITTER_DB_RAW_FILE("\\[SPLIT]"),

  //DB_FILE_DICTIONARY_TEST("/dictionary-EN-test.txt"),
  DB_FILE_DICTIONARY("/dictionary-EN.txt");

  private final String value;

  GlobalStrings(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
