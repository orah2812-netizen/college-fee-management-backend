package com.auca.collegefeemanagement.model;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "cashier")
public class Cashier {
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private String FirstName;
    private String LastName;
    private String staffnumber;

    @ManyToOne
    @JoinColumn(name = "counter_id")
    private Counter counter;

    @ManyToMany
    @JoinTable(
        name = "cashier_fee_type",
        joinColumns = @JoinColumn(name = "cashier_id"),
        inverseJoinColumns = @JoinColumn(name = "fee_type_id")
    )
    private List<FeeType> feeTypes;
    public Cashier() {
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
        this.FirstName = firstName;
    }
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        this.LastName = lastName;
    }
    public String getStaffnumber() {
        return staffnumber;
    }
    public void setStaffnumber(String staffnumber) {
        this.staffnumber = staffnumber;
    }

    public Counter getCounter() {
        return counter;
    }
    public void setCounter(Counter counter) {
        this.counter = counter;
    }
    public List<FeeType> getFeeTypes() {
        return feeTypes;
    }
    public void setFeeTypes(List<FeeType> feeTypes) {
        this.feeTypes = feeTypes;
    }
    
}
