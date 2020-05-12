package com.ospino.events.controller;

import com.ospino.events.model.Event;
import com.ospino.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("events")
public class EventController {

    @Autowired // Dependency injection
    private EventRepository eventRepository;

    /**
     * Get all events
     * - Search Keys
     * - Pagination
     * @return
     */
    @GetMapping
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    };

    /**
     * All Event details
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Object getEvent(@PathVariable("id") long id){
        return eventRepository.findById(id);
    };


    /**
     * Create a new Event
     * @param event
     * @return
     */
    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody Event event){
        eventRepository.save(event);
        return new ResponseEntity<Event>(HttpStatus.CREATED);

    };

    /**
     * Update an event
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") long id){
        Event event = eventRepository.findById(id);
        if (event == null) {
            System.out.println("Contact not found!");
            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }
        event.setId(id);
        eventRepository.save(event);
        return new ResponseEntity<Event>(HttpStatus.OK);
    };


    /**
     * Delete an event
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable("id") long id){
        Event event = eventRepository.findById(id);
        if (event == null) {
            System.out.println("Contact not found!");
            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }

        eventRepository.deleteById(id);
        return new ResponseEntity<Event>(HttpStatus.OK);
    };

}
