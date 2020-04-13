package com.ospino.events.controller;

import com.ospino.events.model.User;
import com.ospino.events.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired // Dependency injection
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){ return null; };

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") long id){ return null; };

    @PostMapping
    public ResponseEntity<User> addUser(){ return null; };

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id){ return null; };

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id){ return null; };


}
