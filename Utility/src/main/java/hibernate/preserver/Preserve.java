/*
 * Created by Dariusz Lelek on 9/11/17 9:58 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.preserver;

import dao.DAO;

public interface Preserve {
  int save(DAO dao);
  void update(DAO dao);
  void saveOrUpdate(DAO dao);
  void delete(DAO dao);
}
