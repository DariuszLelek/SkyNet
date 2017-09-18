/*
 * Created by Dariusz Lelek on 9/18/17 8:24 PM
 * Copyright (c) 2017. All rights reserved.
 */

// TODO rename
package text.sentence;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.*;

@Immutable
public class Sentence {

  private final List<Word> words;
  private final List<String> strings;
  private final Predicate predicate;
  private final Subject subject;
  private final List<Long> numbers;

  public Sentence() {
    words = new ArrayList<>(0);
    strings = new ArrayList<>(0);
    predicate = Predicate.EMPTY;
    subject = Subject.EMPTY;
    numbers = new ArrayList<>(0);
  }

  public Sentence(List<Word> words, List<String> strings,
                  Predicate predicate, Subject subject, List<Long> numbers) {
    this.words = words;
    this.strings = strings;
    this.predicate = predicate;
    this.subject = subject;
    this.numbers = numbers;
  }

  public List<Word> getWords() {
    return Collections.unmodifiableList(words);
  }

  public List<String> getStrings() {
    return Collections.unmodifiableList(strings);
  }

  public Predicate getPredicate() {
    return predicate;
  }

  public Subject getSubject() {
    return subject;
  }

  public List<Long> getNumbers() {
    return Collections.unmodifiableList(numbers);
  }

  public boolean hasNumber(){
    return !numbers.isEmpty();
  }

  public boolean isEmpty(){
    return strings.isEmpty();
  }

  /**
   * @return true if sentence has Subject and Predicate.
   */
  public boolean isValid(){
    return !Subject.EMPTY.equals(subject) && !Predicate.EMPTY.equals(predicate);
  }
}
