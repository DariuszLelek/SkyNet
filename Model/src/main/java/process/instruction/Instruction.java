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

  private final Queue<String> queue;

  public Instruction(Queue<String> queue) {
    this.queue = queue;
  }

  public Instruction() {
    this.queue = new LinkedList<>();
  }

  public final String poll(){
    return queue.poll();
  }

  public Queue<String> getQueue() {
    return queue;
  }

  public final boolean isEmpty(){
    return queue.isEmpty();
  }

  public final void clearInstructions(){
    this.queue.clear();
  }

  public final void add(String text){
    queue.add(text);
  }

  @Override
  public final String toString() {
    return "Instruction{" + String.join(",", new ArrayList<>(queue)) + '}';
  }
}
