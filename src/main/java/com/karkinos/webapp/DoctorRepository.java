package com.karkinos.webapp;
 
import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // @Query(value="SELECT * FROM doctor WHERE specialization = ?1 AND city = ?2",
    // nativeQuery = true
    // )
    //"SELECT specialization , city  FROM doctor")
    //List<Todo> (@Param("searchTerm") String searchTerm);
    //List<Doctor> findBySpecializationandcity(String specialization, String city);
    // List<Doctor> findBySpecialization(String specialization);
    // List<Doctor> findByCity(String city);

    //Object findBySpecialization(String specialization);

    //Object findByCity(String city);
   List<Doctor> findBySpecializationAndCity(String specialization, String city);
   List<Doctor> findAll();
     
    //@Query(value="SELECT d FROM Doctor d where d.specialization = ?0 AND d.city = ?0",nativeQuery=true )
}

