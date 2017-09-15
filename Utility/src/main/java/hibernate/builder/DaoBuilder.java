/*
 * Created by Dariusz Lelek on 9/15/17 8:21 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.builder;

import dao.Dao;

@FunctionalInterface
public interface DaoBuilder<T extends Dao> {
  T build();
}
