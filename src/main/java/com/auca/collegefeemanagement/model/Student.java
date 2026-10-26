package com.auca.collegefeemanagement.model;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "student")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String FirstName;
    private String LastName;

    @Column (unique = true)
    private String regnumber;
    private String program;
    private String phone;

    public Student() {
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        this.FirstName = firstName;}
        public String getLastName() {
        return LastName;
}
public void setLastName(String lastName) {
        this.LastName = lastName;
    }
    public String getRegnumber() {
        return regnumber;
    }
    public void setRegnumber(String regnumber) {
        this.regnumber = regnumber;
    }
    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
