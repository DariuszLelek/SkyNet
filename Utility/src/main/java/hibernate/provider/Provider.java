/*
 * Created by Dariusz Lelek on 9/11/17 10:08 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.provider;

import dao.Dao;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public interface Provider<T> {
  int getQuantity();
  T getByUniqueKey(String propertyName, String value);
  //Collection<T> getByUniqueKeys(String propertyName, Collection<String> values);
  Collection<T> getByKey(String propertyName, String value);
  Collection<T> getByKeys(String propertyName, Collection<String> values);
  Collection<T> getAll();
}
