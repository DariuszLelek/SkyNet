/*
 * Created by Dariusz Lelek on 9/13/17 7:47 PM
 * Copyright (c) 2017. All rights reserved.
 */

package cache;

import java.util.HashMap;
import java.util.Map;

public class GenericCache<K,V> {
  private final Map<K, V> CACHE = new HashMap<>();
}
