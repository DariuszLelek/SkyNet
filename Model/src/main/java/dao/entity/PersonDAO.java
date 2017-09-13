/*
 * Created by Dariusz Lelek on 9/13/17 6:12 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dao.entity;

import dao.DAO;

import javax.persistence.*;

@Entity
@Table(name = "person", catalog = "entity")
public class PersonDAO extends DAO implements java.io.Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "PERSON_ID", unique = true, nullable = false)
  private int personId = 0;
  @Column(name = "FIRST_NAME")
  private String firstName;
  @Column(name = "LAST_NAME")
  private String lastName;
  @Column(name = "EMAIL")
  private String email = "";
  @Column(name = "PHONE")
  private String phone;

  public int getPersonId() {
    return personId;
  }

  public void setPersonId(int personId) {
    this.personId = personId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

}
