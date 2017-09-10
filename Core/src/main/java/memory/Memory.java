/*
 * Created by Dariusz Lelek on 9/10/17 9:52 PM
 * Copyright (c) 2017. All rights reserved.
 */

package memory;

import org.joda.time.DateTime;


public class Memory {
  public static Memory BLANK = new Memory();
  
  private DateTime time;
  private String memoryText;

  public DateTime getTime() {
    return time;
  }

  public void setTime(DateTime time) {
    this.time = time;
  }

  public String getMemoryText() {
    return memoryText;
  }

  public void setMemoryText(String memoryText) {
    this.memoryText = memoryText;
  }
}
