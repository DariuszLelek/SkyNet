/*
 * Created by Dariusz Lelek on 9/14/17 8:31 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dao;

import validator.StringValidator;

public class Dao implements StringValidator {
  @Override
  public String getNonNull(String string) {
    return string != null ? string : "";
  }
}
