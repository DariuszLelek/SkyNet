/*
 * Created by Dariusz Lelek on 9/14/17 7:07 PM
 * Copyright (c) 2017. All rights reserved.
 */

package validator.skill;

import validator.Validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailValidator implements Validator<String> {
  @Override
  public boolean isValid(String string) {
    try {
      InternetAddress emailAddress = new InternetAddress(string);
      emailAddress.validate();
    } catch (AddressException ex) {
      return false;
    }
    return true;
  }
}
