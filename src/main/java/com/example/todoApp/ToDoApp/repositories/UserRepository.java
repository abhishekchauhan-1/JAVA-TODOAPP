package com.example.todoApp.ToDoApp.repositories;

import com.example.todoApp.ToDoApp.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserEmail(String userEmail);
}
