package com.example.todoApp.ToDoApp.repositories;

import com.example.todoApp.ToDoApp.entities.Todos;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<Todos, ObjectId> {

}
