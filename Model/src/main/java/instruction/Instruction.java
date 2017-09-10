package instruction;

import hibernate.Word;

import java.util.LinkedList;
import java.util.Queue;

public class Instruction {
  public final static Instruction EMPTY = new Instruction();

  private final Queue<Word> words;
  private final Queue<Number> numbers;

  public Instruction(Queue<Word> words, Queue<Number> numbers) {
    this.words = words;
    this.numbers = numbers;
  }

  public Instruction() {
    this.words = null;
    this.numbers = null;
  }

  public Queue<Word> getWords() {
    return words != null ? words : new LinkedList<>();
  }

  public Queue<Number> getNumbers() {
    return numbers != null ? numbers : new LinkedList<>();
  }

  @Override
  public String toString() {
    return "Instruction{" +
        "words=" + words +
        ", numbers=" + numbers +
        '}';
  }
}
