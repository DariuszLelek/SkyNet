/*
 * Created by Dariusz Lelek on 9/18/17 8:19 PM
 * Copyright (c) 2017. All rights reserved.
 */

package text;

import com.sun.istack.internal.NotNull;
import constant.WordClass;
import dao.WordDao;
import helper.entity.WordDictionary;
import hibernate.provider.DaoProviderFactory;
import text.sentence.*;
import utilities.NumberUtility;

import java.util.*;
import java.util.stream.Collectors;

public class SentenceAnalyzer {

  public Sentence getSentence(Collection<String> strings) {
    return performAnalyze(strings);
  }

  private Sentence performAnalyze(Collection<String> strings) {
    PairSP pairSubjectPredicate = getPairSP(strings);
    List<Word> words = getWordsList(strings);
    List<Long> numbers = getNumbersList(strings);

    return new Sentence(words, new ArrayList<>(strings), pairSubjectPredicate.getPredicate(),
        pairSubjectPredicate.getSubject(), numbers);
  }

  private List<Long> getNumbersList(Collection<String> strings) {
    return NumberUtility.tryGetNumbersFromWords(strings);
  }

  private List<Word> getWordsList(Collection<String> strings) {
    return strings.stream()
        .map(string -> new Word(string,
            getDescriptions(string),
            getWordType(string),
            getWordClasses(string)))
        .collect(Collectors.toList());
  }

  private Set<String> getDescriptions(String string){
    return WordDictionary.getByString(string)
        .stream().map(WordDao::getDescription).collect(Collectors.toSet());
  }

  private Set<WordClass> getWordClasses(String string) {
    return WordDictionary.getByString(string)
        .stream().map(WordDao::getWordClass).collect(Collectors.toSet());
  }

  private WordType getWordType(String string) {
    return WordType.getByString(string);
  }

  @NotNull
  private PairSP getPairSP(Collection<String> strings) {
    // TODO
    return new PairSP(null, null);
  }

  private class PairSP {
    private final Predicate predicate;
    private final Subject subject;

    public PairSP(Predicate predicate, Subject subject) {
      this.predicate = predicate;
      this.subject = subject;
    }

    public Predicate getPredicate() {
      return predicate;
    }

    public Subject getSubject() {
      return subject;
    }
  }
}
