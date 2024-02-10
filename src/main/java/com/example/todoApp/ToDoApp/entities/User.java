package com.example.todoApp.ToDoApp.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document (collection = "user")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
    private ObjectId id;

    @NonNull
    @Indexed(unique = true)
    private String userEmail;

    private String name;

    @NonNull
    private String password;

    private String jwtToken;

    @DBRef
    private List<Todos> todos = new ArrayList<>();
    private List<String> roles;

    // Constructor with @NonNull fields
    public User(ObjectId id, String userEmail, String password, List<Todos> todos, List<String> roles) {
        this.id = id;
        this.userEmail = userEmail;
        this.password = password;
        this.todos = todos;
        this.roles = roles;
    }
}
