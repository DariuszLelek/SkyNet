/*
 * Created by Dariusz Lelek on 9/10/17 9:52 PM
 * Copyright (c) 2017. All rights reserved.
 */
package memory;

public class MemoryCreator implements MemoryCreate{

  @Override
  public Memory createMemory(String memoryText) {
    return Memory.BLANK;
  }
  
}
