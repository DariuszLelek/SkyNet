/*
 * Created by Dariusz Lelek on 9/18/17 8:24 PM
 * Copyright (c) 2017. All rights reserved.
 */

// TODO rename
package text.sentence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sentence {

  private final Map<String, Word> words;
  private final List<String> strings;
  private final Predicate predicate;
  private final Subject subject;
  private final List<Long> numbers;

  public Sentence(Map<String, Word> words, List<String> strings,
                  Predicate predicate, Subject subject, List<Long> numbers) {
    this.words = words;
    this.strings = strings;
    this.predicate = predicate;
    this.subject = subject;
    this.numbers = numbers;
  }
}
