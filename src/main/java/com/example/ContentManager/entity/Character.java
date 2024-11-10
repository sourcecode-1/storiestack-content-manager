package com.example.ContentManager.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.Date;

@Document(collection = "character")
@Data
public class Character {
    @Id
    private String id;
    private String name;
    private String icon;
    private Date creationDate;
    private String description;

    @DBRef
    private Artist artist;
}