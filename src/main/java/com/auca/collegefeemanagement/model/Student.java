package com.auca.collegefeemanagement.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String regNumber;

    private String program;
    private String phone;

    public Student() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getRegNumber() { return regNumber; }
    public void setRegNumber(String regNumber) { this.regNumber = regNumber; }

    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}