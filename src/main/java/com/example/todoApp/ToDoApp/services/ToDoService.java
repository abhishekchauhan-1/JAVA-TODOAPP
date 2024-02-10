package com.example.todoApp.ToDoApp.services;

import com.example.todoApp.ToDoApp.entities.Todos;
import com.example.todoApp.ToDoApp.entities.User;
import com.example.todoApp.ToDoApp.repositories.ToDoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveTodos(Todos todos, String userEmail){
        try{
            User user = userService.findByUserEmail(userEmail);
            todos.setDate(LocalDateTime.now());
            Todos updatedTodo = toDoRepository.save(todos);
            user.getTodos().add(updatedTodo);
            userService.saveUser(user);
        }catch(Exception e){
            throw new RuntimeException("An error happened",e);
        }
    }

    public Todos saveUpdatedUser(Todos todos){
       return toDoRepository.save(todos);
    }

    public void deleteTodo(Todos entity){
         toDoRepository.delete(entity);
    }

    public Todos findById(ObjectId id){
        return toDoRepository.findById(id).orElse(null);
    }

    public List<Todos> getAllTodos(){
        return toDoRepository.findAll();
    }

}
