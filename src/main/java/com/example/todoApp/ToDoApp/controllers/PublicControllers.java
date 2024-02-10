package com.example.todoApp.ToDoApp.controllers;

import com.example.todoApp.ToDoApp.Security.JwtHelper;
import com.example.todoApp.ToDoApp.entities.JWTRequest;
import com.example.todoApp.ToDoApp.entities.User;
import com.example.todoApp.ToDoApp.repositories.UserRepository;
import com.example.todoApp.ToDoApp.services.UserDetailsServiceImpl;
import com.example.todoApp.ToDoApp.services.UserService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicControllers {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(UserControllers.class);

    @PostMapping("/create-user")
    public boolean saveUser(@RequestBody User user) {
        userService.saveEntry(user);
        return true;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody JWTRequest request) {
        this.doAuthenticate(request.getUserEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserEmail());
        String token = this.helper.generateToken(userDetails);

        User dbUser = userRepository.findByUserEmail(request.getUserEmail());


        User response = User.builder()
                .id(new ObjectId())
                .name(dbUser != null ? dbUser.getName() : null)
                .userEmail(userDetails.getUsername())
                .password(userDetails.getPassword())
                .todos(new ArrayList<>())
                .roles(List.of("USER"))
                .jwtToken(token)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }
}
