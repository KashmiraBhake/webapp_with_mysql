package com.karkinos.webapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "doctor")
public class Doctor {
 
  private long id;
  private String firstName;
  private String lastName;
  private String specialization;
  private int phoneNumber;
  private String address;
  private String city;
  private int pincode;

  protected Doctor() {

  }
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

  public Doctor(String firstName, String lastName, String specialization, int phoneNumber, String address, String city, int pincode) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.specialization = specialization;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.city = city;
    this.pincode = pincode;
  }

  public void setId(long id) {
    this.id = id;
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

  public String getSpecialization() {
    return specialization;
  }

  public void setSpecialization(String specialization) {
    this.specialization = specialization;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(int phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public int getPincode() {
    return pincode;
  }

  public void setPincode(int pincode) {
    this.pincode = pincode;
  }

  
}