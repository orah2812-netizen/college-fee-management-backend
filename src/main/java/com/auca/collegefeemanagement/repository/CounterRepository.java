package com.auca.collegefeemanagement.repository;
import com.auca.collegefeemanagement.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface CounterRepository  extends JpaRepository<Counter, UUID> {
    
}
