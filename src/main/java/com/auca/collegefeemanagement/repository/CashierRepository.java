package com.auca.collegefeemanagement.repository;
import java.util.List;

import com.auca.collegefeemanagement.model.Cashier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CashierRepository extends JpaRepository<Cashier, UUID> {
      @Query("SELECT DISTINCT c FROM Cashier c JOIN c.feeTypes f WHERE LOWER(f.name) = LOWER(:name)")
    List<Cashier> searchByFeeTypeName(@Param("name") String name);
}
    

