/*
 * Created by Dariusz Lelek on 9/14/17 8:31 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dao;

import process.validator.Validator;

public abstract class Dao implements Validator{

  String getNotNull(String string){
    return string != null ? string : "";
  }

  @Override
  public abstract boolean isValid();

  public static class Property {
    private final String value;

    public Property(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }
}
