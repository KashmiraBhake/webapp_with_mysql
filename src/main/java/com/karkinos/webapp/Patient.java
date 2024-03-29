package com.karkinos.webapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity 
@Table(name = "patient")
public class Patient {

  private long id;
  private String firstName;
  private String lastName;
  private String age;
  private String gender;
  private String city;
  private String pincode;
  @Column(nullable = true, length = 64)
  private String photos;

  protected Patient() {

  }
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

  public Patient(String firstName, String lastName, String age, String gender, String city, String pincode, String photos) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.gender = gender;
    this.city = city;
    this.pincode = pincode;
    this.photos=photos;
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

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
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

  public String getPhotos() {
    return photos;
  }
  public void setPhotos(String photos) {
    this.photos = photos;
  }
  @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == 0) return null;
        // if (photos == null) return null;
        return "/patient-photos/" + id + "/" + photos;
    }
}
