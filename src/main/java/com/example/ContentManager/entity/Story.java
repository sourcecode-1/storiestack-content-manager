package com.example.ContentManager.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "story")
@Data
public class Story {
    @Id
    private String id;
    private String title;
    private List<String> images;
    private Integer likeCount;
    private String description;
    private Date creationDate;

    @DBRef
    private Character character;
}