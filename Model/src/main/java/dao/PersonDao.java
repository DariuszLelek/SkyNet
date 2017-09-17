/*
 * Created by Dariusz Lelek on 9/14/17 10:46 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dao;

import process.validator.Validator;

import javax.persistence.*;

@Entity
@Table(name = "person", catalog = "entity")
public class PersonDao extends Dao implements java.io.Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "PERSON_ID", unique = true, nullable = false)
  private int personId;
  @Column(name = "FIRST_NAME")
  private String firstName;
  @Column(name = "LAST_NAME")
  private String lastName;
  @Column(name = "EMAIL")
  private String email;
  @Column(name = "PHONE")
  private String phone;

  public int getPersonId() {
    return personId;
  }

  public void setPersonId(int personId) {
    this.personId = personId;
  }

  public String getFirstName() {
    return getNotNull(firstName);
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return getNotNull(lastName);
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return getNotNull(email);
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return getNotNull(phone);
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public String toString() {
    return "PersonDao{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        '}';
  }

  @Override
  public boolean isValid() {
    return !getFirstName().isEmpty() || !getLastName().isEmpty();
  }
}
