package com.example.todoApp.ToDoApp.controllers;

import com.example.todoApp.ToDoApp.entities.Todos;
import com.example.todoApp.ToDoApp.entities.User;
import com.example.todoApp.ToDoApp.services.ToDoService;
import com.example.todoApp.ToDoApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Component
@RequestMapping("/todos")
@RestController
public class TodoControllers {

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> saveTodos(@RequestBody Todos todos){
       try{
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           String userName = authentication.getName();
           toDoService.saveTodos(todos,userName);
           return  ResponseEntity.ok("To Do Created Successfully");
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping
    public ResponseEntity<?> getAllTodos(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserEmail(userName);
        List<Todos> todos = user.getTodos();
        if(todos!=null && !todos.isEmpty()){
            return new ResponseEntity<>(todos,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/{todoId}")
    public ResponseEntity<?> updateTodo(@PathVariable ObjectId todoId, @RequestBody Todos newTodos) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Todos oldTodos = toDoService.findById(todoId);

        if (oldTodos != null) {
            oldTodos.setTitle(newTodos.getTitle() != null ? newTodos.getTitle() : oldTodos.getTitle());
            oldTodos.setDescription(newTodos.getDescription() != null ? newTodos.getDescription() : oldTodos.getDescription());
            oldTodos.setDueDate(newTodos.getDueDate() != null ? newTodos.getDueDate() : oldTodos.getDueDate());
            oldTodos.setStatus(newTodos.isStatus() != oldTodos.isStatus() ? newTodos.isStatus() : oldTodos.isStatus());

            Todos updatedTodo = toDoService.saveUpdatedUser(oldTodos);

            return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodos(@PathVariable ObjectId todoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Todos todo = toDoService.findById(todoId);
        if (todo != null) {
            toDoService.deleteTodo(todo);
           User user =  userService.findByUserEmail(userName);
           userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Todo not found", HttpStatus.NOT_FOUND);
        }
    }
}