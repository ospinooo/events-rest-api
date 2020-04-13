package com.ospino.events.repository;

import com.ospino.events.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findById(long id);
}
