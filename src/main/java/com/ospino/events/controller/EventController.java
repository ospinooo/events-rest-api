package com.ospino.events.controller;

import com.ospino.events.model.Event;
import com.ospino.events.model.User;
import com.ospino.events.repository.EventRepository;
import com.ospino.events.repository.FeeRepository;
import com.ospino.events.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@CrossOrigin(origins = "https://pabloospino.com", maxAge = 3600)
@RequestMapping("events")
public class EventController {

    @Autowired // Dependency injection
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeeRepository feeRepository;


    private static final Integer PAGE_SIZE = 9;


    /**
     * Get all events
     * - Search Keys
     * - Pagination
     * @return
     */
    @GetMapping
    public Page<Event> getAllEvents(@RequestParam(defaultValue="", name = "search") String search,
                                    @RequestParam(defaultValue="0", name="page") Integer page,
                                    @RequestParam(defaultValue="ASC", name="dir") String direction,
                                    @RequestParam(defaultValue="id", name="sort") String sort,
                                    @RequestParam(defaultValue="", name="date_init") String dateInit,
                                    @RequestParam(defaultValue="", name="date_end") String dateEnd){
        if (!dateInit.isEmpty() && !dateEnd.isEmpty()){
            Date sqlDateInit = Date.valueOf(dateInit);
            Date sqlDateEnd= Date.valueOf(dateEnd);
            return eventRepository.findAllByDate(sqlDateInit,sqlDateEnd ,
                    PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.valueOf(direction), "date")));
        }

        direction = direction.toUpperCase();

        if (!search.isEmpty()){
            return eventRepository.findAllByTitle(search,
                    PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.valueOf(direction), "title")));
        }

        return eventRepository.findAll(
                PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.valueOf(direction), sort)));

    };

    /**
     * All Event details
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Object getEvent(@PathVariable("id") long id){
        return eventRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Fail -> Cause: Event not found"));
    };


    /**
     * Create a new Event
     * @param event
     * @return
     */
    @PostMapping
    public Event createEvent(@RequestBody Event event, @AuthenticationPrincipal UserDetails userDetails){
        // Get the user
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username: " + username));

        // Set the user to the event
        event.setUser(user);
        eventRepository.save(event);

        // Save fees
        event.getFees().forEach(fee -> {
            fee.setEvent(event);
            feeRepository.save(fee);
        });

        return event;
    };

    /**
     * Update an event
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public Event updateEvent(@RequestBody Event event,
                             @PathVariable("id") long id,
                             @AuthenticationPrincipal UserDetails userDetails){

        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username: " + username));

        Event event_prev = eventRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Fail -> Cause: Event not found"));

        event.setUser(user);
        event.setId(id);

        event.getFees().forEach(fee -> {
            fee.setEvent(event);
            feeRepository.save(fee);
        });

        eventRepository.save(event);
        return eventRepository.findById(event.getId()).orElseThrow(
                () -> new RuntimeException("Fail -> Cause: Event not found"));
    }


    /**
     * Delete an event
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable("id") long id){
        Event event = eventRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Fail -> Cause: Event not found"));

        eventRepository.deleteById(id);
        return new ResponseEntity<Event>(HttpStatus.OK);
    };

    /**
     * Delete all events
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Event> deleteEvents(){
        eventRepository.deleteAll();
        return new ResponseEntity<Event>(HttpStatus.OK);
    };


}
