package instruction;

import hibernate.Word;

import java.util.LinkedList;
import java.util.Queue;

public class Instruction {
  public final static Instruction EMPTY = new Instruction();

  private final Queue<Word> words;

  public Instruction(Queue<Word> words, Queue<Number> numbers) {
    this.words = words;
  }

  public Instruction() {
    this.words = null;
  }

  public Queue<Word> getWords() {
    return words != null ? words : new LinkedList<>();
  }

  @Override
  public String toString() {
    return "Instruction{" +
        "words=" + words + '}';
  }
}
