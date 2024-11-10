package com.example.ContentManager.model.output;
import lombok.Data;

import java.util.Date;

@Data
public class CharacterResponseDTO {
    private String id;
    private String name;
    private String icon;
    private Date creationDate;
    private String description;
    private ArtistResponseDTO artist;
}