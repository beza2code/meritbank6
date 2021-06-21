package com.meritbank6.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  private String ssn;

  protected Customer() {}

  public Customer(String firstName, String lastName, String ssn) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.ssn = ssn;
  }

  @Override
  public String toString() {
    return String.format(
        "Customer[id=%d, firstName='%s', lastName='%s', SSN='%s']",
        id, firstName, lastName, ssn);
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

public String getSsn() {
	return ssn;
}

}
