/*
 * Created by Dariusz Lelek on 9/11/17 10:08 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.provider;

import java.util.Collection;
import java.util.List;

public interface Provider<T> {
  int getQuantity();
  T getByUniqueKey(String criterion, String value);
  Collection<T> getByUniqueKeys(String criterion, Collection<String> values);
  Collection<T> getAll();
}
