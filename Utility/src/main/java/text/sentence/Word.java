/*
 * Created by Dariusz Lelek on 9/18/17 8:31 PM
 * Copyright (c) 2017. All rights reserved.
 */

package text.sentence;

import constant.WordClass;

public class Word {
  private final WordType wordType;
  private final WordClass wordClass;

  public Word(WordType wordType, WordClass wordClass) {
    this.wordType = wordType;
    this.wordClass = wordClass;
  }

  public WordClass getWordClass() {
    return wordClass;
  }

  public WordType getWordType() {
    return wordType;
  }
}
