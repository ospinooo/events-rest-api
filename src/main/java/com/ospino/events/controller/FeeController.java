package com.ospino.events.controller;

import com.ospino.events.model.Event;
import com.ospino.events.model.Fee;
import com.ospino.events.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fees")
public class FeeController {

    @Autowired // Dependency injection
    private FeeRepository feeRepository;

    @GetMapping
    public List<Fee> getAllFees(){ return feeRepository.findAll(); };

    @GetMapping("/{id}")
    public Fee getFee(@PathVariable("id") long id){
        return feeRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Fail -> Cause: Fee not found"));
    };

    @PostMapping
    public ResponseEntity<Fee> addFee(){ return null; };

    @PutMapping("/{id}")
    public ResponseEntity<Fee> updateFee(@PathVariable("id") long id){
        Fee fee = feeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Fail -> Cause: Fee not found"));

        fee.setId(id);
        feeRepository.save(fee);
        return new ResponseEntity<Fee>(HttpStatus.OK);
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<Fee> deleteFee(@PathVariable("id") long id){
        Fee fee = feeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Fail -> Cause: Fee not found"));

        if (fee == null) {
            System.out.println("Fee not found!");
            return new ResponseEntity<Fee>(HttpStatus.NOT_FOUND);
        }
        feeRepository.deleteById(id);
        return new ResponseEntity<Fee>(HttpStatus.OK);
    };

}
