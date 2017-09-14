/*
 * Created by Dariusz Lelek on 9/14/17 7:11 PM
 * Copyright (c) 2017. All rights reserved.
 */

package validator;

@FunctionalInterface
public interface Validator<T> {
  boolean isValid(T object);
}
