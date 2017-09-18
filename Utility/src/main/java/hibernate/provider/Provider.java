/*
 * Created by Dariusz Lelek on 9/11/17 10:08 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.provider;

import dao.Dao;

import java.util.Collection;

public interface Provider<T> {
  int getQuantity();
  Collection<T> getByKey(Dao.Property property, Object value);
  Collection<T> getByKeys(Dao.Property property, Collection values);
  Collection<T> getAll();
}
