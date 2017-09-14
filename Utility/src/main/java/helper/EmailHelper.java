/*
 * Created by Dariusz Lelek on 9/13/17 7:41 PM
 * Copyright (c) 2017. All rights reserved.
 */

package helper;

import dao.PersonDAO;
import helper.entity.PersonHelper;
import hibernate.provider.DAOProvider;
import validator.skill.EmailValidator;


import java.util.ArrayList;
import java.util.Collection;

public class EmailHelper {
  private final EmailValidator emailValidator = new EmailValidator();
  private final float MATCH_PERCENT = 0.8F;
  private final DAOProvider<PersonDAO> personProvider = new DAOProvider<>(PersonDAO.class);

  private final String[] MAIL_AT = {"at", "@"};
  private final String[] MAIL_DOT = {".", "dot"};

  public String getEmail(Collection<String> chunks) {
    String candidate;

    candidate = tryGetValidMailFromChunks(chunks);
    if (emailValidator.isValid(candidate)) {
      return candidate;
    }

    candidate = tryGetValidMailFromPersons(chunks);
    if (emailValidator.isValid(candidate)) {
      return candidate;
    }

    candidate = tryAssembleValidMailFromChunks(chunks);
    if (emailValidator.isValid(candidate)) {
      return candidate;
    }

    return "";
  }

  private String tryGetValidMailFromPersons(Collection<String> chunks){
    final Collection<PersonDAO> persons = personProvider.getAll();
    final Collection<PersonDAO> foundPersons = new ArrayList<>();

    chunks.forEach(chunk -> {
      addAllProbablePersonsToFoundPersons(
          PersonHelper.filterPersonsByNameWithProbability(MATCH_PERCENT, chunk, persons),
          foundPersons);
    });

    // When found more than one person filter results by last name
    if(foundPersons.size() > 1){
      persons.clear();

      chunks.forEach(chunk -> {
        addAllProbablePersonsToFoundPersons(
            PersonHelper.filterPersonsByLastNameWithProbability(MATCH_PERCENT, chunk, foundPersons),
            persons);
      });

      foundPersons.clear();
      foundPersons.addAll(persons);
    }

    return getEmailFromFirstFoundPersonOrDefault(foundPersons);
  }

  private String getEmailFromFirstFoundPersonOrDefault(Collection<PersonDAO> persons){
    return persons.stream().findFirst().orElse(new PersonDAO()).getEmail();
  }

  private static void addAllProbablePersonsToFoundPersons(final Collection<PersonDAO> probablePersons,
                                                          final Collection<PersonDAO> foundPersons){
    for(PersonDAO probablePerson : probablePersons){
      if(!foundPersons.contains(probablePerson)){
        foundPersons.add(probablePerson);
      }
    }
  }

  private String tryGetValidMailFromChunks(Collection<String> chunks){
    return chunks.stream().filter(emailValidator::isValid).findFirst().orElse("");
  }

  private String tryAssembleValidMailFromChunks(Collection<String> chunks){
    // TODO;
    return "";
  }
}
