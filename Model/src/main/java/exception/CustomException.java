/*
 * Created by Dariusz Lelek on 9/20/17 6:17 PM
 * Copyright (c) 2017. All rights reserved.
 */

package exception;

public abstract class CustomException extends Throwable {
  final String message;

  public CustomException(String message) {
    this.message = message;
  }
}
