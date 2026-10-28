package com.auca.collegefeemanagement.repository;
import com.auca.collegefeemanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface StudentRepository extends JpaRepository<Student, UUID> {
    
    List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}
