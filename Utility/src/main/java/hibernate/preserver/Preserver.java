/*
 * Created by Dariusz Lelek on 9/11/17 9:58 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.preserver;

import java.util.Collection;

public interface Preserver<T> {
  void save(T dao);
  void update(T dao);
  void saveOrUpdate(T dao);
  void delete(T dao);
  void save(Collection<T> daos);
  void update(Collection<T> daos);
  void saveOrUpdate(Collection<T> daos);
  void delete(Collection<T> daos);
}
