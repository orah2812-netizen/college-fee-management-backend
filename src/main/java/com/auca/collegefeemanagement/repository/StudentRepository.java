package com.auca.collegefeemanagement.repository;
import com.auca.collegefeemanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;


public interface StudentRepository extends JpaRepository<Student, UUID> {
    
    List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

        @Query("SELECT DISTINCT p.student FROM FeePayment p WHERE p.cashier.id = :cashierId")
    List<Student> findStudentsOfCashier(@Param("cashierId") UUID cashierId);
    
  
}
