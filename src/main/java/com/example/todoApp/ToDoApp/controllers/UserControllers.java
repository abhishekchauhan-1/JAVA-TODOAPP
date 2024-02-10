package com.example.todoApp.ToDoApp.controllers;
import com.example.todoApp.ToDoApp.entities.User;
import com.example.todoApp.ToDoApp.services.UserDetailsServiceImpl;
import com.example.todoApp.ToDoApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/User")
@Component
public class UserControllers {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;



    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
       User userInDB = userService.findByUserEmail(userName);
       if(userInDB!=null){
           userInDB.setName(user.getName());
           userInDB.setUserEmail(user.getUserEmail());
           userInDB.setPassword(user.getPassword());
           userService.saveEntry(userInDB);
           return new ResponseEntity<>(userInDB, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
