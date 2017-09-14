/*
 * Created by Dariusz Lelek on 9/13/17 7:41 PM
 * Copyright (c) 2017. All rights reserved.
 */

package helper;

import config.DataBaseConfig;
import dao.entity.PersonDAO;
import helper.entity.PersonHelper;
import hibernate.provider.DataProvider;
import validator.skill.EmailValidator;


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.Collection;

public class EmailHelper {
  private final DataProvider personDataProvider = new DataProvider(DataBaseConfig.PROD);
  private final EmailValidator emailValidator = new EmailValidator();
  private final float MATCH_PERCENT = 0.8F;

  private final String[] MAIL_AT = {"at", "@"};
  private final String[] MAIL_DOT = {".", "dot"};

  public String getEmail(String object) {
    // TODO try get from person
    return emailValidator.isValid(object) ? object : "";
  }

  public String getEmail(Collection<String> objects) {
    String candidate = tryGetMailFromChunks(objects);

    // TODO implement strategy pattern
    if (emailValidator.isValid(candidate)) {
      return candidate;
    } else {
      candidate = tryGetValidMailFromPersons(objects);
      if (emailValidator.isValid(candidate)) {
        return candidate;
      } else {
        // TODO tryGetValidMailFromChunks()
        return "";
      }
    }
  }

  private String tryGetValidMailFromChunks(Collection<String> chunks){
    // TODO
    return "";
  }

  private String tryGetValidMailFromPersons(Collection<String> chunks){
    final Collection<PersonDAO> persons = personDataProvider.getAllEntities(PersonDAO.class);
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

  private String tryGetMailFromChunks(Collection<String> chunks){
    return chunks.stream().filter(emailValidator::isValid).findFirst().orElse("");
  }

}
