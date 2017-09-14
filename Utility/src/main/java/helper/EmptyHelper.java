/*
 * Created by Dariusz Lelek on 9/13/17 7:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

package helper;

import java.util.Collection;

public class EmptyHelper implements Helper {
  static final EmptyHelper INSTANCE = new EmptyHelper();


  @Override
  public Object get(Object object) {
    return null;
  }

  @Override
  public Object get(Collection objects) {
    return null;
  }
}
