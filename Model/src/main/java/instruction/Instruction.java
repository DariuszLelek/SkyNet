/*
 * Created by Dariusz Lelek on 9/10/17 9:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

package instruction;

import dictionary.Word;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

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
        "words=" + String.join(",", words.stream().map(Word::getWord).collect(Collectors.toList())) + '}';
  }
}
