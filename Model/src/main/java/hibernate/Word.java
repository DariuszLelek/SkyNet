package hibernate;


import global.GlobalStrings;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "word", catalog = "dictionary")
public class Word implements java.io.Serializable {
  public static final Word UNKNOWN = new Word();

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "WORD_ID", unique = true, nullable = false)
  private int wordId;
  @Column(name = "WORD", unique = true, nullable = false)
  private String word;
  @Column(name = "WORD_CLASS", nullable = false)
  private String wordClassString;
  @Column(name = "SYNONYMS")
  private String synonyms;

  public Word() {
    this.word = "";
    this.wordClassString = WordClass.UNKNOWN.toString();
    this.synonyms = "";
  }

  public Word(String word, String wordClassString, String synonyms) {
    this.word = word;
    this.wordClassString = wordClassString;
    this.synonyms = synonyms;
  }

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
    return synonyms;
  }

  public void setSynonyms(String synonyms) {
    this.synonyms = synonyms;
  }

  public WordClass getWordClass(){
    return WordClass.getEnumByValue(wordClassString);
  }

  public List<String> getSynonymsList() {
    List<String> synonymsList = Arrays.stream(synonyms.split(GlobalStrings.SYNONYM_SPLITTER.toString()))
        .collect(Collectors.toList());

    return synonymsList != null ? synonymsList : new ArrayList<>();
  }
}


