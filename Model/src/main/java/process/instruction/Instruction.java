/*
 * Created by Dariusz Lelek on 9/12/17 11:50 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process.instruction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Instruction {
  public final static Instruction EMPTY = new Instruction();

  private final Queue<String> instructions;

  public Instruction(Queue<String> instructions) {
    this.instructions = instructions;
  }

  public Instruction() {
    this.instructions = new LinkedList<>();
  }

  public Queue<String> getInstructions() {
    return instructions;
  }

  public final boolean hasInstructions(){
    return !instructions.isEmpty();
  }

  @Override
  public final String toString() {
    return "Instruction{" + String.join(",", new ArrayList<>(instructions)) + '}';
  }
}
