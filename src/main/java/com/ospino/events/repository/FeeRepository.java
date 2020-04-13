package com.ospino.events.repository;

import com.ospino.events.model.Fee;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FeeRepository extends JpaRepository<Fee,Long> {
    Fee findById(long id);
}
