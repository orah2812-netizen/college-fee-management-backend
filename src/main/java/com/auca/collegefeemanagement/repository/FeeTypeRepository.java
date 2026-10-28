package com.auca.collegefeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auca.collegefeemanagement.model.FeeType;

import java.util.*;

import org.springframework.data.jpa.repository.Query;

public interface FeeTypeRepository extends JpaRepository<FeeType, UUID> {
    
  @Query("SELECT f FROM FeeType f WHERE f.cashiers IS EMPTY")
    List<FeeType> findUnused();
}