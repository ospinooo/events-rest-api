package com.ospino.events.controller;

import com.ospino.events.model.User;
import com.ospino.events.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("users")
public class UserController {

    @Autowired // Dependency injection
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){ return userRepository.findAll(); };

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") long id){ return userRepository.findById(id); };

    @GetMapping("/username/{username}")
    public User getUser(@PathVariable("username") String username){
        return userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("Fail -> Cause: Username not found"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id){
        User user = userRepository.findById(id);
        if (user == null) {
            System.out.println("User not found!");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<User>(HttpStatus.OK);
    };


    /*
    @PostMapping
    public ResponseEntity<User> addUser(){ return null; };

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id){ return null; };

    */


}
