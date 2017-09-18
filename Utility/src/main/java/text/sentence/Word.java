/*
 * Created by Dariusz Lelek on 9/18/17 8:31 PM
 * Copyright (c) 2017. All rights reserved.
 */

package text.sentence;

import constant.WordClass;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class Word {
  private final String string;
  private final Set<String> descriptions;
  private final WordType wordType;
  private final Set<WordClass> wordClasses;

  public Word(String string, Set<String> descriptions, WordType wordType, Set<WordClass> wordClasses) {
    this.string = string;
    this.descriptions = descriptions;
    this.wordType = wordType;
    this.wordClasses = wordClasses;
  }

  public String getString() {
    return string;
  }

  public WordType getWordType() {
    return wordType;
  }

  public Collection<WordClass> getWordClasses() {
    return Collections.unmodifiableSet(wordClasses);
  }

  public boolean isClass(WordClass wordClass){
    return wordClasses.contains(wordClass);
  }

  public Set<String> getDescriptions() {
    return descriptions;
  }
}
