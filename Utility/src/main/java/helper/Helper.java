/*
 * Created by Dariusz Lelek on 9/13/17 7:39 PM
 * Copyright (c) 2017. All rights reserved.
 */

package helper;

import java.util.Collection;

public interface Helper<T> {
  public abstract T get(T object);
  public abstract T get(Collection<T> objects);
}
