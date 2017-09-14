/*
 * Created by Dariusz Lelek on 9/13/17 9:35 PM
 * Copyright (c) 2017. All rights reserved.
 */

package helper.entity;

import dao.entity.PersonDAO;
import utilities.StringUtilities;

import java.util.Collection;
import java.util.stream.Collectors;

public class PersonHelper {
  public static Collection<PersonDAO> filterPersonsByNameWithProbability(
      final float probability,
      final String name,
      final Collection<PersonDAO> persons) {
    return persons.stream()
        .filter(p -> StringUtilities.firstInSecondPercent(name, p.getFirstName()) >= probability)
        .collect(Collectors.toList());
  }

  public static Collection<PersonDAO> filterPersonsByLastNameWithProbability(
      final float probability,
      final String lastName,
      final Collection<PersonDAO> persons) {
    return persons.stream()
        .filter(p -> StringUtilities.firstInSecondPercent(lastName, p.getLastName()) >= probability)
        .collect(Collectors.toList());
  }
}
