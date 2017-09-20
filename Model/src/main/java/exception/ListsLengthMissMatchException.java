/*
 * Created by Dariusz Lelek on 9/17/17 7:13 PM
 * Copyright (c) 2017. All rights reserved.
 */

package exception;

public class ListsLengthMissMatchException extends CustomException{
  public ListsLengthMissMatchException(String message) {
    super(message);
  }

  @Override
  public String toString() {
    return "ListsLengthMissMatchException{}";
  }
}
