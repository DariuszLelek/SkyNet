/*
 * Created by Dariusz Lelek on 9/10/17 9:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

package processable;

public abstract class Processable {

  public abstract void execute();

  public abstract boolean canProcess();

  public abstract int getPriority();

  public boolean isNotEmpty(){
    return true;
  }

}
