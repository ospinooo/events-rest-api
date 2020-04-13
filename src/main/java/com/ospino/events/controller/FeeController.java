package com.ospino.events.controller;

import com.ospino.events.model.Fee;
import com.ospino.events.model.User;
import com.ospino.events.repository.FeeRepository;
import com.ospino.events.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fees")
public class FeeController {

    @Autowired // Dependency injection
    private FeeRepository feeRepository;

    @GetMapping
    public List<Fee> getAllFees(){ return null; };

    @GetMapping("/{id}")
    public Fee getFee(@PathVariable("id") long id){ return null; };

    @PostMapping
    public ResponseEntity<Fee> addFee(){ return null; };

    @PutMapping("/{id}")
    public ResponseEntity<Fee> updateFee(@PathVariable("id") long id){ return null; };

    @DeleteMapping("/{id}")
    public ResponseEntity<Fee> deleteFee(@PathVariable("id") long id){ return null; };

}
