package com.example.todoApp.ToDoApp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
@Document
@Data
@NoArgsConstructor

public class Todos {
    @Id
    @NonNull
    private ObjectId id;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private boolean status;
    @NonNull
    private Date dueDate;

    private LocalDateTime date;

}
