package com.example.todoApp.ToDoApp.services;

import com.example.todoApp.ToDoApp.entities.User;
import com.example.todoApp.ToDoApp.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;



    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    public void saveEntry(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }


    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }


    public User findByUserEmail(String userEmail){
        return userRepository.findByUserEmail(userEmail);
    }

    public void deleteById(ObjectId id){
       userRepository.deleteById(id);
    }
}
