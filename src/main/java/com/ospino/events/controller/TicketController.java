package com.ospino.events.controller;

import com.ospino.events.model.Ticket;
import com.ospino.events.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpServerErrorException.NotImplemented;

import java.util.List;

@RestController
@RequestMapping("tickets")
public class TicketController {

    @Autowired // Dependency injection
    private TicketRepository ticketRepository;

    @GetMapping
    public List<Ticket> getAllTickets(){ return null; };

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable("id") long id){ return null; };

    @PostMapping
    public ResponseEntity<Ticket> addTicket(){ return null; };

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") long id){ return null; };

    @DeleteMapping("/{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable("id") long id){ return null; };

}
