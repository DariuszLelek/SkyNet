/*
 * Created by Dariusz Lelek on 9/17/17 1:35 AM
 * Copyright (c) 2017. All rights reserved.
 */

package helper;

import dao.PersonDao;
import helper.entity.PersonHelper;
import hibernate.provider.DaoProviderFactory;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.Collection;

public class EmailHelper {
  private final float MATCH_PERCENT = 0.8F;

  private final String[] MAIL_AT = {"at", "@"};
  private final String[] MAIL_DOT = {".", "dot"};

  public String getEmail(Collection<String> chunks) {
    String candidate;

    candidate = tryGetValidMailFromChunks(chunks);
    if (isValid(candidate)) {
      return candidate;
    }

    candidate = tryGetValidMailFromPersons(chunks);
    if (isValid(candidate)) {
      return candidate;
    }

    candidate = tryAssembleValidMailFromChunks(chunks);
    if (isValid(candidate)) {
      return candidate;
    }

    return "";
  }

  private boolean isValid(String emailString) {
    try {
      InternetAddress emailAddress = new InternetAddress(emailString);
      emailAddress.validate();
    } catch (AddressException ex) {
      return false;
    }
    return true;
  }

  private String tryGetValidMailFromPersons(Collection<String> chunks){
    final Collection<PersonDao> persons = DaoProviderFactory.getPersonProvider().getAll();
    final Collection<PersonDao> foundPersons = new ArrayList<>();

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

  private String getEmailFromFirstFoundPersonOrDefault(Collection<PersonDao> persons){
    return persons.stream().findFirst().orElse(new PersonDao()).getEmail();
  }

  private static void addAllProbablePersonsToFoundPersons(final Collection<PersonDao> probablePersons,
                                                          final Collection<PersonDao> foundPersons){
    for(PersonDao probablePerson : probablePersons){
      if(!foundPersons.contains(probablePerson)){
        foundPersons.add(probablePerson);
      }
    }
  }

  private String tryGetValidMailFromChunks(Collection<String> chunks){
    return chunks.stream().filter(this::isValid).findFirst().orElse("");
  }

  private String tryAssembleValidMailFromChunks(Collection<String> chunks){
    // TODO;
    return "";
  }

  @Override
  protected void finalize() throws Throwable {
    System.out.println("destroyed");
  }
}
