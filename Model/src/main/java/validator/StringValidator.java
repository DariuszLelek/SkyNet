/*
 * Created by Dariusz Lelek on 9/15/17 9:28 PM
 * Copyright (c) 2017. All rights reserved.
 */

package validator;

@FunctionalInterface
public interface StringValidator {
  String getNonNull(String string);
}
