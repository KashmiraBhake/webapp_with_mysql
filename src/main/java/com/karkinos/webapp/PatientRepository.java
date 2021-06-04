package com.karkinos.webapp;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
 
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByFirstName(String firstName);
    List<Patient> findByIdAndDocName(long id,String docName);
    // @Query("SELECT new Patient(p.id,p.docName) FROM Patient p")
    // List<Patient> findAll();
 
}