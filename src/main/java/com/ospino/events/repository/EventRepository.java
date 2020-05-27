package com.ospino.events.repository;

import com.ospino.events.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    Optional<Event> findById(long id);

    @Query("select e from Event e where LOWER(e.title) like LOWER(CONCAT('%',:title,'%'))")
    Page<Event> findByTitle(@Param("title") String title, Pageable pageRequest);
}
