/*
 * Created by Dariusz Lelek on 9/10/17 9:54 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dictionary.word;

import dictionary.DictionaryFactory;
import hibernate.Word;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WordProvider {

  public Word getWord(String wordString){
    wordString = formatWordString(wordString);
    return validateWord(DictionaryFactory.getDictionaryProvider()
        .getEntityByUniqueKey(Word.class, "word", wordString), wordString);
  }

  public List<Word> getWords(String[] wordStrings){
    return Arrays.stream(wordStrings)
        .map(this::getWord)
        .collect(Collectors.toList());
  }

  private String formatWordString(String wordString){
    return wordString.toLowerCase();
  }

  private Word validateWord(Object object, String wordString){
    Word word;

    if(object instanceof Word){
      word = (Word) object;
    }else{
      word = new Word();
      word.setWord(wordString);
    }

    return word;
  }
}
