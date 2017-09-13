/*
 * Created by Dariusz Lelek on 9/13/17 7:45 PM
 * Copyright (c) 2017. All rights reserved.
 */

package helper;

import java.util.HashMap;
import java.util.Map;

public class HelperFactory {
  private static final Map<HelperType, Helper> CACHE = new HashMap<>();

  static {
    CACHE.put(HelperType.EMAIL, new EmailHelper());
  }

  public static Helper getHelper(HelperType helperType){
    return getFromCache(helperType);
  }

  private static Helper getFromCache(HelperType helperType){
    return CACHE.getOrDefault(helperType, EmptyHelper.INSTANCE);
  }
}
