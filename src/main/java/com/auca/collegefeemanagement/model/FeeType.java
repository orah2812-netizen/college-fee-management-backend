package com.auca.collegefeemanagement.model;

import java.util.UUID;

import jakarta.persistence.*;


@Entity
@Table(name = "fee_type")

public class FeeType {

     @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    public FeeType() {
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
