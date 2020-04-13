package com.ospino.events.repository;

import com.ospino.events.model.Event;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    Event findById(long id);
}
