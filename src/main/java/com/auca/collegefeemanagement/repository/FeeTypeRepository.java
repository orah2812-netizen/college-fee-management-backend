package com.auca.collegefeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.auca.collegefeemanagement.model.FeeType;
import java.util.UUID;

public interface FeeTypeRepository extends JpaRepository<FeeType, UUID> {
    
}
