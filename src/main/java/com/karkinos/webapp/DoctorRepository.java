package com.karkinos.webapp;
 
import java.util.List;

//import org.hibernate.query.NativeQuery;
//import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

//import jdk.nashorn.internal.runtime.Specialization;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
   
    List<Doctor> findBySpecializationAndCity(String specialization, String city);
}

