package com.example.ContentManager.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "artist")
@Data
public class Artist {
    @Id
    private String id;
    private String name;
    private String email;
    private String description;
}

