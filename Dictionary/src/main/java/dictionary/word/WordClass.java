/*
 * Created by Dariusz Lelek on 9/11/17 10:09 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dictionary.word;

public enum WordClass {
  UNKNOWN("unknown"),

  NOUN("noun"),
  VERB("verb"),
  ADJECTIVE("adjective"),
  PRONOUN("pronoun"),
  PREPOSITION("preposition"),
  CONJUNCTION("conjunction"),
  DETERMINER("determiner"),
  EXCLAMATION("exclamation");

  private final String value;

  WordClass(String value) {
    this.value = value;
  }

  public static WordClass getEnumByValue(String value){
    for(WordClass wordClass : values()){
      if(wordClass.getValue().equalsIgnoreCase(value)){
        return wordClass;
      }
    }
    return UNKNOWN;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "WordClass{\'" + value + "\'}";
  }
}
