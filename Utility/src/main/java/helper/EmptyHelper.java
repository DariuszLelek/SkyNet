/*
 * Created by Dariusz Lelek on 9/13/17 7:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

package helper;

import java.util.Collection;

public class EmptyHelper implements Helper {
  static final EmptyHelper INSTANCE = new EmptyHelper();

  @Override
  public boolean isValid(String text) {
    return false;
  }

  @Override
  public String getValid(String text) {
    return "";
  }

  @Override
  public String getValid(Collection<String> texts) {
    return "";
  }

}
