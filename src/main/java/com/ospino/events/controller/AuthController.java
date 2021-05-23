package com.ospino.events.controller;

import com.ospino.events.message.request.LoginForm;
import com.ospino.events.message.request.SignUpForm;
import com.ospino.events.message.response.JwtResponse;
import com.ospino.events.message.response.ResponseMessage;
import com.ospino.events.message.response.ValidUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

import com.ospino.events.model.Role;
import com.ospino.events.model.RoleName;
import com.ospino.events.model.User;
import com.ospino.events.repository.RoleRepository;
import com.ospino.events.repository.UserRepository;
import com.ospino.events.security.jwt.JwtProvider;

@RestController
@CrossOrigin(origins = "https://pabloospino.com/*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthController {

    // Authentication
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    // Password
    @Autowired
    PasswordEncoder passwordEncoder;

    // JWT
    @Autowired
    JwtProvider jwtProvider;

    /**
     * Sign In
     * @param loginRequest
     * @return
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) ((org.springframework.security.core.Authentication) authentication).getPrincipal();

        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername(), userDetails.getAuthorities()));
    }


    /**
     * Valid
     * @param username
     * @param email
     * @return
     */
    @GetMapping("/valid")
    public ResponseEntity<?> validUser(@RequestParam(defaultValue="", name = "username") String username,
                             @RequestParam(defaultValue="", name = "email") String email) {
        Boolean usernameValid = username.isEmpty() ?
                false : !userRepository.existsByUsername(username);

        Boolean emailValid = email.isEmpty() ?
                false : !userRepository.existsByEmail(email);

        return ResponseEntity.ok(new ValidUserResponse(usernameValid, emailValid));
    }




    /**
     * Sign Up
     * @param signUpRequest is a body parameter of type SignUpForm
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken."), HttpStatus.BAD_REQUEST);
        }

        // New User
        User user = new User(
                signUpRequest.getName(),
                signUpRequest.getUsername(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail()
        );

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail -> Cause: Admin Role not found."));
                    roles.add(adminRole);
                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail -> Cause: User Role not found."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully."), HttpStatus.OK);
    }

}
