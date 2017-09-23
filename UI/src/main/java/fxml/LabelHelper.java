/*
 * Created by Dariusz Lelek on 9/23/17 9:35 PM
 * Copyright (c) 2017. All rights reserved.
 */

package fxml;

import styles.LabelEnum;

public class LabelHelper {
  public static String getTextByEnabled(boolean enabled){
    return enabled ? LabelEnum.ACTIVE_TEXT.toString() : LabelEnum.INACTIVE_TEXT.toString();
  }

  public static String getStyleByEnabled(boolean enabled){
    return enabled ? LabelEnum.ACTIVE_STYLE.toString() : LabelEnum.INACTIVE_STYLE.toString();
  }
}
