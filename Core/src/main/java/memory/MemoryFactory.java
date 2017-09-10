/*
 * Created by Dariusz Lelek on 9/10/17 9:52 PM
 * Copyright (c) 2017. All rights reserved.
 */
package memory;

public class MemoryFactory {
  private static final MemoryProvider memoryProvider = new MemoryProvider();
  private static final MemoryPreserver memoryPreserver = new MemoryPreserver();
  private static final MemoryCreator memoryCreator = new MemoryCreator();

  public static MemoryProvider getMemoryProvider() {
    return memoryProvider;
  }

  public static MemoryPreserver getMemoryPreserver() {
    return memoryPreserver;
  }

  public static MemoryCreator getMemoryCreator() {
    return memoryCreator;
  }
}
