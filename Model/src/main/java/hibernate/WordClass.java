/*
 * Created by Dariusz Lelek on 9/10/17 9:54 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate;

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
      if(wordClass.toString().equalsIgnoreCase(value)){
        return wordClass;
      }
    }
    return UNKNOWN;
  }

  @Override
  public String toString() {
    return value;
  }
}
