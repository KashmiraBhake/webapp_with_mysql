package com.karkinos.webapp;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByFirstName(String firstName);
    List<Patient> findAll();
    List<Patient> findById(String id);
}