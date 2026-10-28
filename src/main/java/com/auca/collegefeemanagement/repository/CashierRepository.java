package com.auca.collegefeemanagement.repository;
import com.auca.collegefeemanagement.model.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CashierRepository extends JpaRepository<Cashier, UUID> {
    
}
