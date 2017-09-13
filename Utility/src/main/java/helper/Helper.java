/*
 * Created by Dariusz Lelek on 9/13/17 7:39 PM
 * Copyright (c) 2017. All rights reserved.
 */

package helper;

import java.util.Collection;

public interface Helper {
  public abstract boolean isValid(String text);
  public abstract String getValid(String text);
  public abstract String getValid(Collection<String> texts);
}
