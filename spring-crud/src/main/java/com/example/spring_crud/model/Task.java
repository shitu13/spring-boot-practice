package com.example.spring_crud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private String taskId;
    private String description;
    private int severity;
    private String assignee;
    private int storyPoint;
}
