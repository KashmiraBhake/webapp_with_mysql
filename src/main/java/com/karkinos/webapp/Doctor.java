package com.karkinos.webapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity 
@Table(name = "doctor")
public class Doctor {
 
  private long id;

  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9'.]{3,50}$" , message = "only alphabets, numbers, apostrophe, dot characters are allowed, Firstname must be 3-50 characters long")
  private String firstName;

  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9'.]{3,50}$" , message = "only alphabets, numbers, apostrophe, dot characters are allowed, Firstname must be 3-50 characters long")
  private String lastName;

  @NotBlank
  @Pattern(regexp = "^[A-Za-z]{3,100}$", message = "only alphabets are allowed")
  private String specialization;

  @NotBlank
  @Pattern(regexp="^[1-9][0-9]{9}$", message="Mobile number is invalid")
  private String phoneNumber;

  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9,'.]{10,2000}$",message = "Address must be min 10 and max 2000 chars long. only alphabets, numbers, comma, dot, apostrophe characters are allowed")
  private String address;

  @NotBlank
  @Pattern(regexp = "^[A-Za-z]{3,100}$", message = "Please enter between {min} and {max}")
  private String city;

  @NotBlank
  @Pattern(regexp="^[1-9][0-9]{5}$", message="Pincode is invalid")
  private String pincode;

  protected Doctor() {

  }
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

  public Doctor(String firstName, String lastName, String specialization, String phoneNumber, String address, String city, String pincode) {
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

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
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

  public String getPincode() {
    return pincode;
  }

  public void setPincode(String pincode) {
    this.pincode = pincode;
  }
  

  
}