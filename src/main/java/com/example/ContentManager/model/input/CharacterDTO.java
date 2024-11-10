package com.example.ContentManager.model.input;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.bson.types.ObjectId;
import jakarta.validation.constraints.NotBlank;

@Data
public class CharacterDTO {
    @NotBlank(message = "Invalid artistId: Must not be null, empty, whitespaces or incorrect format.")
    @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "Invalid artistId: Must not be null, empty, whitespaces or incorrect format.")
    private String artistId;
    @NotBlank(message = "Character name is required")
    private String name;
    @NotBlank(message = "Character icon is required")
    private String icon;
    private String description;

}