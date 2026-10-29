package com.auca.collegefeemanagement.controller;
import com.auca.collegefeemanagement.model.PaymentStatus;

import com.auca.collegefeemanagement.model.FeePayment;
import com.auca.collegefeemanagement.service.service.FeePaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/fee-payments")
public class FeePaymentController {

    @Autowired 
    private FeePaymentService feePaymentService;

       @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody FeePayment payment) {
        String message = feePaymentService.save(payment);
        if (message.equals("Fee payment date cannot be in the past")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (message.equals("Cashier already has a payment booked on that date")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<FeePayment> getAll() {
        return feePaymentService.getAll();
    }   
       @GetMapping("/by-status")
   public List<FeePayment> getByStatus(@RequestParam PaymentStatus status) {
       return feePaymentService.getByStatus(status);
   } 
     @GetMapping("/between")
       public List<FeePayment> getBetween(@RequestParam String start, @RequestParam String end) {
           LocalDate startDate = LocalDate.parse(start);
           LocalDate endDate = LocalDate.parse(end);
           return feePaymentService.getBetween(startDate, endDate);
       }




    @GetMapping ("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<FeePayment> feePayment = feePaymentService.getById(id);
        if (feePayment.isPresent()) {
            return ResponseEntity.ok(feePayment.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The fee payment with that id does not exist");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody FeePayment fee) {
        String message = feePaymentService.update(id, fee);
        if (message.equals("The fee payment with that id does not exist")) {


            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }

       @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = feePaymentService.delete(id);
        if (message.equals("The fee payment with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else if (message.equals("That fee payment has receipts, delete them first")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        return ResponseEntity.ok(message);
    }
       @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable UUID id, @RequestParam PaymentStatus status) {
        String message = feePaymentService.updateStatus(id, status);
        if (message.equals("The fee payment with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else if (message.startsWith("Cannot change status")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
        @GetMapping("/stats/by-status")
    public List<Object[]> getStatsByStatus() {
        return feePaymentService.getStatsByStatus();
    }
}