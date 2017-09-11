/*
 * Created by Dariusz Lelek on 9/10/17 9:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

package config;

public enum GlobalStrings {
  SYNONYM_SPLITTER(",");

  private final String value;

  GlobalStrings(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
