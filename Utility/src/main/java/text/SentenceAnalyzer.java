/*
 * Created by Dariusz Lelek on 9/18/17 8:19 PM
 * Copyright (c) 2017. All rights reserved.
 */

package text;

import com.sun.istack.internal.NotNull;
import helper.entity.WordHelper;
import javafx.util.Pair;
import text.sentence.*;
import utilities.NumberUtility;
import utilities.StringUtility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SentenceAnalyzer {

  public Sentence getSentence(Collection<String> strings){
    return performAnalyze(strings);
  }

  private Sentence performAnalyze(Collection<String> strings){
    PairSP pairSubjectPredicate = getPairSP(strings);
    Map<String, Word> words = getWordMap(strings);
    List<Long> numbers = getNumbers(strings);

    return new Sentence(words, new ArrayList<>(strings) , pairSubjectPredicate.getPredicate(),
        pairSubjectPredicate.getSubject(), numbers);
  }

  private List<Long> getNumbers(Collection<String> strings){
    return NumberUtility.tryGetNumbersFromWords(strings);
  }

  private Map<String, Word> getWordMap(Collection<String> strings){


    return null;
  }

  @NotNull
  private PairSP getPairSP(Collection<String> strings){



    return new PairSP(null, null);
  }

  private WordType getWordType(String string){
    //if(StringUtility.isAlphabetic(string) && WordHelper.isValidWord(string))

    return null;
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
