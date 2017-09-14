/*
 * Created by Dariusz Lelek on 9/11/17 9:58 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.preserver;

import dao.DAO;

public interface Preserver<T> {
  int save(T dao);
  void update(T dao);
  void saveOrUpdate(T dao);
  void delete(T dao);
}
