package com.karkinos.webapp;
 

import java.util.List;



//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
//import org.springframework.data.domain.Page;



import org.springframework.data.jpa.repository.JpaRepository;
 
public interface DocumentsRepository extends JpaRepository<Documents, Long> {

   List<Documents> findByPatients(Patient patient_id);
     //Page<Documents> findByPatientId(Long patientId, Pageable pageable);
    //Optional<Documents> findByIdAndPatientId(Long id, Long patientId);


   
}