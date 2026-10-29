package com.auca.collegefeemanagement.model;

import java.time.LocalDate;
import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "receipt")

public class Receipt {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

      private String feeItem;
    private String note;
    private int  amount;
    private LocalDate  issuedDate;

    @ManyToOne 
    @JoinColumn(name = "feepayment_id")
    private FeePayment feePayment;

    public Receipt() {
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getFeeItem() {
        return feeItem;
    }
    public void setFeeItem(String feeItem) {
        this.feeItem = feeItem;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public LocalDate getIssuedDate() {
        return issuedDate;
    }
    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }
    public FeePayment getFeePayment() {
        return feePayment;
    }
    public void setFeePayment(FeePayment feePayment) {
        this.feePayment = feePayment;
    }

}
