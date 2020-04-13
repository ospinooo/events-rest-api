package com.ospino.events.repository;

import com.ospino.events.model.Ticket;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Ticket findById(long id);
}
