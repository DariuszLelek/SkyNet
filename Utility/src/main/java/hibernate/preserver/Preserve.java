/*
 * Created by Dariusz Lelek on 9/11/17 9:58 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.preserver;

public interface Preserve {
  <T> int saveEntity(T entity);
  <T> void updateEntity(T entity);
  <T> void saveOrUpdateEntity(T entity);
  <T> void deleteEntity(T entity);
}
