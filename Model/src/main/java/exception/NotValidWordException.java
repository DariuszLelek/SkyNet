/*
 * Created by Dariusz Lelek on 9/16/17 6:41 PM
 * Copyright (c) 2017. All rights reserved.
 */

package exception;

public class NotValidWordException extends CustomException {
  public NotValidWordException(String message) {
    super(message);
  }

  @Override
  public String toString() {
    return "NotValidWordException{\"" + message + "\" is not a valid word.}";
  }
}
