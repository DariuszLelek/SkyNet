/*
 * Created by Dariusz Lelek on 9/14/17 8:31 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dao;

public class Dao {
  String getNonNull(String string) {
    return string != null ? string : "";
  }
}
