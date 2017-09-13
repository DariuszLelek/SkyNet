/*
 * Created by Dariusz Lelek on 9/13/17 7:24 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dao.entity;

import dao.DAO;

import javax.persistence.*;

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

  public int getWordId() {
    return wordId;
  }

  public void setWordId(int wordId) {
    this.wordId = wordId;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getWordClassString() {
    return wordClassString;
  }

  public void setWordClassString(String wordClassString) {
    this.wordClassString = wordClassString;
  }

  public String getSynonyms() {
    return synonyms != null ? synonyms : "";
  }

  public void setSynonyms(String synonyms) {
    this.synonyms = synonyms;
  }

  @Override
  public String toString() {
    return "Word{word='" + word + '\'' +
        ", wordClassString='" + wordClassString + '\'' +
        ", synonyms='" + synonyms + '\'' +
        '}';
  }

}

