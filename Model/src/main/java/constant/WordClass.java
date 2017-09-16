/*
 * Created by Dariusz Lelek on 9/17/17 12:31 AM
 * Copyright (c) 2017. All rights reserved.
 */

package constant;

public enum WordClass {
  UNKNOWN("unknown"),

  PLURAL("plural"),

  NOUN("noun"),
  VERB("verb"),
  ADJECTIVE("adjective"),
  ADVERB("adverb"),
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
