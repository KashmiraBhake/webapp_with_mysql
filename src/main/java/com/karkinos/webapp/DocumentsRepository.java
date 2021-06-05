package com.karkinos.webapp;
 
//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
public interface DocumentsRepository extends JpaRepository<Documents, Long> {
   
}