package com.example.ContentManager.model.input;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class StoryUpdateDTO {
    @NotBlank(message = "Invalid storyId: Must not be null, empty, whitespaces or incorrect format.")
    @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "Invalid storyId: Must not be null, empty, whitespaces or incorrect format.")
    private String storyId;
    private String title;
    private String description;
}
