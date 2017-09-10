/*
 * Created by Dariusz Lelek on 9/10/17 9:52 PM
 * Copyright (c) 2017. All rights reserved.
 */
package memory;

public class MemoryProvider implements MemoryProvide{

  @Override
  public Memory provideMemory(String memoryText) {
    return Memory.BLANK;
  }

  
}
