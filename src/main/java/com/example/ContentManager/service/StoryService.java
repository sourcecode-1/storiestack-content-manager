package com.example.ContentManager.service;

import com.example.ContentManager.entity.Story;
import com.example.ContentManager.model.input.StoryDTO;
import com.example.ContentManager.model.input.StoryUpdateDTO;
import com.example.ContentManager.model.output.StoryResponseDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface StoryService {
    StoryResponseDTO addStoryToCharacter(StoryDTO story);
    List<StoryResponseDTO> searchStories(String searchText);
    Boolean isStoryTitleExists(String storyTitle);
    List<StoryResponseDTO> getStoriesUnderCharacter(ObjectId characterId);
    Integer incrementLikeCount(ObjectId storyObjectId);
    StoryResponseDTO updateStory(StoryUpdateDTO storyUpdateDTO);
    Boolean deleteStory(ObjectId storyObjectId);
}
