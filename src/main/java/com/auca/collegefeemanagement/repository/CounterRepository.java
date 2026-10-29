package com.auca.collegefeemanagement.repository;
import com.auca.collegefeemanagement.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;


public interface CounterRepository  extends JpaRepository<Counter, UUID> {

       @Query("SELECT l FROM Counter l WHERE NOT EXISTS (SELECT c FROM Cashier c WHERE c.counter = l) ORDER BY l.counterNumber")
    List<Counter> findFreeCounters();
    Optional<Counter> findByCounterNumber(int counterNumber);  
    
}
