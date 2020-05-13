package com.ospino.events.controller;

import com.ospino.events.message.request.EventPurchaseForm;
import com.ospino.events.model.Event;
import com.ospino.events.model.Fee;
import com.ospino.events.model.Ticket;
import com.ospino.events.model.User;
import com.ospino.events.repository.EventRepository;
import com.ospino.events.repository.FeeRepository;
import com.ospino.events.repository.TicketRepository;
import com.ospino.events.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tickets")
public class TicketController {

    @Autowired // Dependency injection
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeeRepository feeRepository;


    /**
     * Get all the tickets
     * @return
     */
    @GetMapping
    public List<Ticket> getAllTickets(){ return ticketRepository.findAll(); };

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable("id") long id){ return ticketRepository.findById(id); };

    /**
     * This could be transfer ticket to someone
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") long id){ return null; };

    /**
     * Delete ticket
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable("id") long id){
        Ticket ticket = ticketRepository.findById(id);
        if (ticket == null) {
            System.out.println("Ticket not found!");
            return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
        }
        ticketRepository.deleteById(id);
        return new ResponseEntity<Ticket>(HttpStatus.OK);
    };


    /**
     * Buy a ticket
     * @param body
     * @param event_id
     * @return
     */
    @PostMapping()
    public ResponseEntity<Event> buyTickets(@RequestBody List<EventPurchaseForm> body,
                                            @RequestParam("event") Long event_id){

        Event event = eventRepository.findById(event_id).orElseThrow(() ->
                new RuntimeException("Fail -> Cause: Event not found."));

        if (event.getFees().size() != body.size()){
            throw new RuntimeException("Fail -> Cause: Event fee number is different than the body given");
        }

        body.forEach(eventPurchaseForm -> {
            Fee fee = feeRepository.findById(eventPurchaseForm.getFeeId());

            userRepository.findAllById(eventPurchaseForm.getUsers()).forEach(user -> {
                Ticket ticket = new Ticket(fee, user);
                ticketRepository.save(ticket);
            });
        });

        return new ResponseEntity<Event>(HttpStatus.CREATED);
    };
}
