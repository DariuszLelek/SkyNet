/*
 * Created by Dariusz Lelek on 9/11/17 10:09 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dictionary.word;


import config.GlobalStrings;
import hibernate.entity.dictionary.WordDAO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Word extends WordDAO {

  public Word() {
    super();
  }

  public Word(String word, String wordClassString, String synonyms) {
    super(word, wordClassString, synonyms);
  }

  public WordClass getWordClass() {
    return WordClass.getEnumByValue(getWordClassString());
  }

  public List<String> getSynonymsList() {
    return Arrays.stream(getSynonyms().split(GlobalStrings.SYNONYM_SPLITTER.getValue()))
        .collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return super.toString();
  }
}


