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
    public ResponseEntity<String> save(@RequestBody FeePayment feePayment) {
        String message = feePaymentService.save(feePayment);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
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
        } else {
            return ResponseEntity.ok(message);
        }
    }
    
}
