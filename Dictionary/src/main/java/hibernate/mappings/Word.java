package hibernate.mappings;
// Generated Sep 9, 2017 9:52:23 PM by Hibernate Tools 4.3.1


import javax.persistence.*;

@Entity
@Table(name = "word", catalog = "dictionary")
public class Word implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "WORD_ID", unique = true, nullable = false)
  private int wordId;
  @Column(name = "WORD_STRING", unique = true, nullable = false)
  private String wordString;
  @Column(name = "COMMAND", columnDefinition = "boolean default false")
  private boolean command;
  @Column(name = "QUESTION", columnDefinition = "boolean default false")
  private boolean question;
  // probably not needed
  @Column(name = "SYNONYMS")
  private String synonyms;

  public Word() {
    this.wordString = "";
    this.command = false;
    this.question = false;
    this.synonyms = "";
  }

  public Word(String wordString, boolean command, boolean question, String synonyms) {
    this.wordId = wordId;
    this.wordString = wordString;
    this.command = command;
    this.question = question;
    this.synonyms = synonyms;
  }

  public int getWordId() {
    return wordId;
  }

  public void setWordId(int wordId) {
    this.wordId = wordId;
  }

  public String getWordString() {
    return wordString;
  }

  public void setWordString(String wordString) {
    this.wordString = wordString;
  }

  public boolean isCommand() {
    return command;
  }

  public void setCommand(boolean command) {
    this.command = command;
  }

  public boolean isQuestion() {
    return question;
  }

  public void setQuestion(boolean question) {
    this.question = question;
  }

  public String getSynonyms() {
    return synonyms;
  }

  public void setSynonyms(String synonyms) {
    this.synonyms = synonyms;
  }
}


