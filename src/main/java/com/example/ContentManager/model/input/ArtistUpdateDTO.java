package com.example.ContentManager.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ArtistUpdateDTO {
    @NotBlank(message = "Invalid artistId: Must not be null, empty, whitespaces or incorrect format.")
    @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "Invalid artistId: Must not be null, empty, whitespaces or incorrect format.")
    private String artistId;
    private String name;
    private String description;
}
