package com.example.ContentManager.util;

import com.example.ContentManager.entity.Artist;
import com.example.ContentManager.entity.Story;
import com.example.ContentManager.model.output.ArtistResponseDTO;
import com.example.ContentManager.model.output.CharacterResponseDTO;
import com.example.ContentManager.model.output.StoryResponseDTO;
import org.bson.types.ObjectId;
import org.springframework.util.StringUtils;

public class AppUtils {
    public static boolean isValidId(String id) {
        return StringUtils.hasText(id) && id.matches("^[a-fA-F0-9]{24}$");
    }
}
