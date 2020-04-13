package com.ospino.events.controller;

import com.ospino.events.model.Event;
import com.ospino.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("events")
public class EventController {

    @Autowired // Dependency injection
    private EventRepository eventRepository;

    @GetMapping
    public List<Event> getAllEvents(){ return null; };

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable("id") long id){ return null; };

    @PostMapping
    public ResponseEntity<Event> addEvent(){ return null; };

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") long id){ return null; };

    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable("id") long id){ return null; };

}
