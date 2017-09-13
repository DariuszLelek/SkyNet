/*
 * Created by Dariusz Lelek on 9/11/17 10:09 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dictionary.word;


import config.GlobalStrings;
import dao.DAO;
import dao.DAOProvide;
import dictionary.WordClass;
import dao.entity.WordDAO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Word implements DAOProvide{
  private final WordDAO wordDAO;

  public Word(WordDAO wordDAO) {
    this.wordDAO = wordDAO;
  }

  public Word(String word, WordClass wordClass) {
    this.wordDAO = new WordDAO();

    this.wordDAO.setWordClassString(wordClass.getValue());
    this.wordDAO.setWord(word);
  }

  public WordClass getWordClass() {
    return WordClass.getEnumByValue(wordDAO.getWordClassString());
  }

  public List<String> getSynonymsList() {
    return Arrays.stream(wordDAO.getSynonyms().split(GlobalStrings.SYNONYM_SPLITTER.getValue()))
        .collect(Collectors.toList());
  }


  @Override
  public String toString() {
    return super.toString();
  }

  @Override
  public DAO getDAO() {
    return wordDAO;
  }
}


