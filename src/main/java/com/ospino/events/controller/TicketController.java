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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
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

    private static final int PAGE_SIZE = 16;

    /**
     * Get all the tickets
     * @return
     */
    @GetMapping
    public Page<Ticket> getAllTickets(@AuthenticationPrincipal UserDetails userDetails,
                                      @RequestParam(defaultValue="0", name="page") Integer page,
                                      @RequestParam(defaultValue="id", name="sort") String sort){

        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() ->
                new RuntimeException("Fail -> Cause: User not found."));

        return ticketRepository.findByUser(user.getId(), PageRequest.of(page, PAGE_SIZE));
    };

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable("id") long id){
        return ticketRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Fail -> Cause: Ticket not found."));
    };

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
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Fail -> Cause: Ticket not found."));

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
    public ResponseEntity<Ticket> buyTickets(@RequestBody List<EventPurchaseForm> body,
                                             @RequestParam("event") Long event_id,
                                             @AuthenticationPrincipal UserDetails userDetails){

        Event event = eventRepository.findById(event_id).orElseThrow(() ->
                new RuntimeException("Fail -> Cause: Event not found."));

        if (event.getFees().size() != body.size()){
            throw new RuntimeException("Fail -> Cause: Event fee number is different than the body given");
        }

        body.forEach(eventPurchaseForm -> {
            Fee fee = feeRepository.findById(eventPurchaseForm.getFeeId()).orElseThrow(() ->
                    new RuntimeException("Fail -> Cause: Fee not found."));

            User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() ->
                            new RuntimeException("Fail -> Cause: User not found."));

            eventPurchaseForm.getAssistants().forEach(assistant -> {
                System.out.println(assistant.getName() + assistant.getId());
                Ticket ticket = new Ticket(fee, user, assistant.getName(), assistant.getId());
                ticketRepository.save(ticket);
            });
        });

        return new ResponseEntity<Ticket>(HttpStatus.CREATED);
    };


    @PatchMapping("/{id}/confirm")
    public ResponseEntity<Ticket> confirmTicket(@PathVariable("id") Long id){
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Fail -> Cause: Ticket not found."));;

        ticket.setConfirmed(true);
        ticketRepository.save(ticket);
        return new ResponseEntity<Ticket>(HttpStatus.OK);
    };

}
