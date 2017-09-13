/*
 * Created by Dariusz Lelek on 9/11/17 10:17 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dictionary.access;

import config.DataBaseConfig;
import hibernate.preserver.DataPreserver;

public class WordPreserver extends DataPreserver{

  public WordPreserver() {
    super(DataBaseConfig.PROD);
  }

}
