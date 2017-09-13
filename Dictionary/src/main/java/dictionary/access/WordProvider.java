/*
 * Created by Dariusz Lelek on 9/11/17 10:17 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dictionary.access;

import config.DataBaseSchema;
import dao.dictionary.WordDAO;
import dictionary.word.Word;
import hibernate.provider.DataProvider;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class WordProvider extends DataProvider {

  public WordProvider() {
    super(DataBaseSchema.DICTIONARY);
  }

  public Word getWord(String wordString){
    wordString = formatWordString(wordString);
    return validateWord(getEntityByUniqueKey(Word.class, "word", wordString), wordString);
  }

  public List<Word> getWords(Collection<String> wordStrings){
    return wordStrings.stream()
        .map(this::getWord)
        .collect(Collectors.toList());
  }

  private String formatWordString(String wordString){
    return wordString.toLowerCase();
  }

  private Word validateWord(Object object, String wordString){
    Word word;

    if(Word.class.isInstance(object)){
      word = (Word) object;
    }else{
      word = new Word();
      word.setWord(wordString);
    }

    return word;
  }
}
