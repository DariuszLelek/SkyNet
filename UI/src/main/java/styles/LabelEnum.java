/*
 * Created by Dariusz Lelek on 9/23/17 9:30 PM
 * Copyright (c) 2017. All rights reserved.
 */

package styles;

public enum LabelEnum {
  ACTIVE_TEXT("Active"),
  INACTIVE_TEXT("Inactive"),

  ACTIVE_STYLE("-fx-text-fill: green;"),
  INACTIVE_STYLE("-fx-text-fill: red;");

  private final String value;

  LabelEnum(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
