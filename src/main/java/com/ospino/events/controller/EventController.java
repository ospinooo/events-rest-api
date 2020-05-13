package com.ospino.events.controller;

import com.ospino.events.message.request.EventPurchaseForm;
import com.ospino.events.model.Event;
import com.ospino.events.model.Fee;
import com.ospino.events.model.User;
import com.ospino.events.repository.EventRepository;
import com.ospino.events.repository.FeeRepository;
import com.ospino.events.repository.UserRepository;
import com.ospino.events.security.jwt.JwtProvider;
import com.ospino.events.security.services.UserDetailsServiceImpl;
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

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("events")
public class EventController {

    @Autowired // Dependency injection
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeeRepository feeRepository;


    private static final Integer PAGE_SIZE = 10;


    /**
     * Get all events
     * - Search Keys
     * - Pagination
     * @return
     */
    @GetMapping
    public Page<Event> getAllEvents(@RequestParam(defaultValue="id", name = "search") String search_key,
                                    @RequestParam(defaultValue="0", name="page") Integer page,
                                    @RequestParam(defaultValue="ASC", name="dir") String direction){
        direction = direction.toUpperCase();
        return eventRepository.findAll(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(Sort.Direction.valueOf(direction), search_key)));
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
    public ResponseEntity<Event> createEvent(@RequestBody Event event, @AuthenticationPrincipal UserDetails userDetails){
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

        return new ResponseEntity<Event>(HttpStatus.CREATED);
    };

    /**
     * Update an event
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") long id){
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Fail -> Cause: Event not found"));;

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
        Event event = eventRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Fail -> Cause: Event not found"));

        eventRepository.deleteById(id);
        return new ResponseEntity<Event>(HttpStatus.OK);
    };

}
