package com.ospino.events.repository;

import com.ospino.events.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Optional<Ticket> findById(long id);

    @Query("select t from Ticket t where t.user.id = :id")
    Page<Ticket> findByUser(@Param("id") long id, Pageable pageRequest);
}
