package com.ospino.events.controller;

import com.ospino.events.model.Event;
import com.ospino.events.model.Fee;
import com.ospino.events.repository.EventRepository;
import com.ospino.events.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://pabloospino.com", maxAge = 3600)
@RequestMapping("fees")
public class FeeController {

    @Autowired // Dependency injection
    private FeeRepository feeRepository;

    @Autowired // Dependency injection
    private EventRepository eventRepository;


    @GetMapping
    public List<Fee> getAllFees(){ return feeRepository.findAll(); };

    @GetMapping("/{id}")
    public Fee getFee(@PathVariable("id") long id){
        return feeRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Fail -> Cause: Fee not found"));
    };

    @PostMapping("/event/{id}")
    public Fee addFee(@PathVariable("id") long id, @RequestBody Fee fee){
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Fail -> Cause: Event not found"));

        fee.setEvent(event);
        feeRepository.save(fee);

        event.addFee(fee);
        eventRepository.save(event);

        return fee;
    };

    @PutMapping("/{id}")
    public Fee updateFee(@RequestBody Fee fee, @PathVariable("id") long id){
        Fee fee_prev = feeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Fail -> Cause: Fee not found"));

        Event event = eventRepository.findById(fee_prev.getEventId()).orElseThrow(
                () -> new RuntimeException("Fail -> Cause: Event not found"));

        fee.setId(id);
        fee.setEvent(event);
        feeRepository.save(fee);
        return fee;
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
