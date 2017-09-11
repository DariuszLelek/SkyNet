/*
 * Created by Dariusz Lelek on 9/11/17 10:41 PM
 * Copyright (c) 2017. All rights reserved.
 */

package instruction;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Instruction {
  public final static Instruction EMPTY = new Instruction();

  private final Queue<String> instructions;

  public Instruction(Queue<String> instructions, Queue<Number> numbers) {
    this.instructions = instructions;
  }

  public Instruction() {
    this.instructions = null;
  }

  public Queue<String> getInstructions() {
    return instructions != null ? instructions : new LinkedList<>();
  }

  @Override
  public String toString() {
    return "Instruction{" +
        "instructions=" + String.join(",",instructions.stream().collect(Collectors.toList())) +
        '}';
  }
}
