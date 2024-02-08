package com.example.todoApp.ToDoApp.services;

import com.example.todoApp.ToDoApp.entities.User;
import com.example.todoApp.ToDoApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        User user = userRepository.findByUserEmail(userEmail);
        if(user!=null){
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .builder().username(user.getUserEmail()).password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0])).build();
        return userDetails;
        }

        throw new UsernameNotFoundException("User Not found with username "+ userEmail);
    }
}

