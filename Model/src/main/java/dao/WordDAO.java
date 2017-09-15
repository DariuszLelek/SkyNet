/*
 * Created by Dariusz Lelek on 9/14/17 10:46 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dao;

import entity.WordClass;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "word", catalog = "entity")
public class WordDAO extends DAO implements java.io.Serializable {
  @Id()
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "WORD_ID", unique = true, nullable = false)
  private int wordId;
  @Column(name = "WORD", unique = true, nullable = false)
  private String word;
  @Column(name = "WORD_CLASS", nullable = false)
  private String wordClassString;
  @Column(name = "SYNONYMS")
  private String synonyms;
  @Column(name = "DESCRIPTION")
  private String description;

  public WordDAO() {
  }

  public WordDAO(String word, String wordClassString) {
    this.word = word;
    this.wordClassString = wordClassString;
  }

  public int getWordId() {
    return wordId;
  }

  public void setWordId(int wordId) {
    this.wordId = wordId;
  }

  public String getWord() {
    return getString(word);
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getWordClassString() {
    return getString(wordClassString);
  }

  public void setWordClassString(String wordClassString) {
    this.wordClassString = wordClassString;
  }

  public String getSynonyms() {
    return getString(synonyms);
  }

  public void setSynonyms(String synonyms) {
    this.synonyms = synonyms;
  }

  public String getDescription() {
    return getString(description);
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public WordClass getWordClass(){
    return WordClass.getEnumByValue(getWordClassString());
  }

  public Collection<String> getSynonymsList(){
    return Arrays.asList(getSynonyms().split(","));
  }

  @Override
  public String toString() {
    return "Word{word='" + word + '\'' +
        ", wordClassString='" + wordClassString + '\'' +
        ", synonyms='" + synonyms + '\'' +
        '}';
  }

}

