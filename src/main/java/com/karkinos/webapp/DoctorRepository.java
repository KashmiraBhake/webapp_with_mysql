package com.karkinos.webapp;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query(value="SELECT d FROM doctor d WHERE " +
    "LOWER(d.specialization) LIKE LOWER(CONCAT('%',:specialization, '%')) AND" +
    "LOWER(d.city) LIKE LOWER(CONCAT('%',:city, '%'))",
    nativeQuery = true
    )
    //"SELECT specialization , city  FROM doctor")
    //List<Todo> (@Param("searchTerm") String searchTerm);
    List<Doctor> findBySpecialization(@Param("specialization")String specialization);
    List<Doctor> findByCity(@Param("city")String city);
}

