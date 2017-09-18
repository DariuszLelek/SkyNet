/*
 * Created by Dariusz Lelek on 9/18/17 8:24 PM
 * Copyright (c) 2017. All rights reserved.
 */

package text.sentence;

import utilities.StringUtility;

public enum WordType {
  TEXT, NUMBER, SYMBOL, UNKNOWN;

  public static WordType getByString(String string){
    if(StringUtility.isAlphabetic(string)){
      return TEXT;
    }else if(StringUtility.isNumeric(string)){
      return NUMBER;
    }else if(!string.isEmpty()){
      return SYMBOL;
    }else{
      return UNKNOWN;
    }
  }
}
