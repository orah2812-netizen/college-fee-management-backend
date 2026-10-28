package com.auca.collegefeemanagement.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cashier")
public class Cashier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String staffNumber;

    @ManyToOne
    @JoinColumn(name = "counter_id")
    private Counter counter;

    @ManyToMany
    @JoinTable(
        name = "cashier_fee_type",
        joinColumns = @JoinColumn(name = "cashier_id"),
        inverseJoinColumns = @JoinColumn(name = "fee_type_id")
    )
    private List<FeeType> feeTypes = new ArrayList<>();

    public Cashier() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getStaffNumber() { return staffNumber; }
    public void setStaffNumber(String staffNumber) { this.staffNumber = staffNumber; }

    public Counter getCounter() { return counter; }
    public void setCounter(Counter counter) { this.counter = counter; }

    public List<FeeType> getFeeTypes() { return feeTypes; }
    public void setFeeTypes(List<FeeType> feeTypes) { this.feeTypes = feeTypes; }
}