package com.example.ContentManager.model.output;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StoryResponseDTO {
    private String id;
    private String title;
    private List<String> images;
    private Integer likeCount;
    private String description;
    private Date creationDate;
    private CharacterResponseDTO character;
}