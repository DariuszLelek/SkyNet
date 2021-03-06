/*
 * Created by Dariusz Lelek on 9/14/17 10:46 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dao;

import constant.WordClass;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "word", catalog = "entity")
public class WordDao extends Dao implements java.io.Serializable {
  public static final int DESCRIPTION_LENGTH = 500;

  @Id()
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "WORD_ID", unique = true, nullable = false)
  private int wordId;
  @Column(name = "WORD", nullable = false)
  private String word;
  @Column(name = "WORD_CLASS")
  private String wordClassString;
  @Column(name = "SYNONYMS")
  private String synonyms;
  @Column(name = "DESCRIPTION", length = DESCRIPTION_LENGTH)
  private String description;

  public static final Property WORD = new Property("word");
  public static final Property CLASS = new Property("wordClassString");
  public static final Property SYNONYMS = new Property("synonyms");

  public WordDao() {
  }

  public WordDao(String word, String wordClassString, String description) {
    this.word = word;
    this.wordClassString = wordClassString;
    this.description = description;
  }

  public WordDao(String word, String wordClassString) {
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
    return getNotNull(word);
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getWordClassString() {
    return getNotNull(wordClassString);
  }

  public void setWordClassString(String wordClassString) {
    this.wordClassString = wordClassString;
  }

  public String getSynonyms() {
    return getNotNull(synonyms);
  }

  public void setSynonyms(String synonyms) {
    this.synonyms = synonyms;
  }

  public String getDescription() {
    return getNotNull(description);
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
    return "WordDao{" +
        "wordId=" + wordId +
        ", word='" + word + '\'' +
        ", wordClassString='" + wordClassString + '\'' +
        ", synonyms='" + synonyms + '\'' +
        ", description='" + description + '\'' +
        '}';
  }

  @Override
  public boolean isValid() {
    return !getWord().isEmpty();
  }
}

