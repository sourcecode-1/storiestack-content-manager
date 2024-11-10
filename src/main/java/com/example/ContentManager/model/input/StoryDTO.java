package com.example.ContentManager.model.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Data
public class StoryDTO {
    @NotBlank(message = "Invalid characterId: Must not be null, empty, whitespaces or incorrect format.")
    @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "Invalid characterId: Must not be null, empty, whitespaces or incorrect format.")
    private String characterId;
    @NotBlank(message = "Story title is required")
    private String title;
    @NotEmpty(message = "Story images are required")
    @Size(min = 1, message = "There must be at least one image")
    private List<String> images;
    @NotBlank(message = "Story description is required")
    private String description;
}