package com.ospino.events.repository;

import com.ospino.events.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeeRepository extends JpaRepository<Fee,Long> {
    Optional<Fee> findById(long id);
}
