package skill.model;

import hibernate.mappings.Word;

import java.util.LinkedList;
import java.util.Queue;

public class Instruction {
  public final static Instruction EMPTY = new Instruction();

  private final Queue<Word> words;
  private final Queue<Integer> numbers;

  public Instruction(Queue<Word> words, Queue<Integer> numbers) {
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

  public Queue<Integer> getNumbers() {
    return numbers != null ? numbers : new LinkedList<>();
  }
}
