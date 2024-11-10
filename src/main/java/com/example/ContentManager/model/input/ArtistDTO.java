package com.example.ContentManager.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArtistDTO {
    @NotBlank(message = "Artist name is required")
    private String name;
    @NotBlank(message = "Artist email is required")
    private String email;
    private String description;
}

