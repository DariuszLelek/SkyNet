/*
 * Created by Dariusz Lelek on 9/11/17 10:08 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.provider;

import java.util.Collection;
import java.util.List;

public interface Provide {
  int getNumberOfEntities(Class clazz);
  Object getEntityByUniqueKey(Class clazz, String criterion, String value);
  Collection getEntitiesByUniqueKeys(Class clazz, String criterion, String[] values);
  Collection getAllEntities(Class clazz);
}
