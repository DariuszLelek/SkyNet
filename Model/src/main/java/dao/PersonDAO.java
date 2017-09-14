/*
 * Created by Dariusz Lelek on 9/14/17 10:46 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dao;

import dao.DAO;

import javax.persistence.*;

@Entity
@Table(name = "person", catalog = "entity")
public class PersonDAO extends DAO implements java.io.Serializable {
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
    return getString(firstName);
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return getString(lastName);
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return getString(email);
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return getString(phone);
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

}
