package com.example.ContentManager.service.impl;

import com.example.ContentManager.entity.Character;
import com.example.ContentManager.entity.Story;
import com.example.ContentManager.exception.*;
import com.example.ContentManager.mapper.StoryMapper;
import com.example.ContentManager.model.input.StoryDTO;
import com.example.ContentManager.model.input.StoryUpdateDTO;
import com.example.ContentManager.model.output.StoryResponseDTO;
import com.example.ContentManager.repository.StoryRepository;
import com.example.ContentManager.service.CharacterService;
import com.example.ContentManager.service.StoryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private StoryMapper storyMapper;

    private static final Integer DEFAULT_LIKE_COUNT = 0;

    @Override
    public StoryResponseDTO addStoryToCharacter(StoryDTO storyDTO) {
        ObjectId characterObjectId = new ObjectId(storyDTO.getCharacterId());
        Optional<Character> characterOptional = characterService.getCharacterById(characterObjectId);
        if (characterOptional.isEmpty()) {
            throw new CharacterNotFoundException("Character not found with id: " + characterObjectId);
        }
        if (isStoryTitleExists(storyDTO.getTitle())) {
            throw new DuplicateStoryTitleException("A story with the title '" + storyDTO.getTitle() + "' already exists.");
        }
        Character character = characterOptional.get();
        Story story = new Story();
        story.setTitle(storyDTO.getTitle());
        story.setImages(storyDTO.getImages());
        story.setLikeCount(DEFAULT_LIKE_COUNT);
        story.setCreationDate(new Date());
        story.setDescription(storyDTO.getDescription());
        story.setCharacter(character);

        try {
            Story savedStory = storyRepository.save(story);
            return storyMapper.convertStoryToStoryResponseDTO(savedStory);
        } catch (DataAccessException e) {
            throw new StorySaveException("Failed to save the story due to a database error.", e);
        }
    }


    @Override
    @Cacheable(value = "searchStories", key = "#searchText")
    public List<StoryResponseDTO> searchStories(String searchText) {
        List<Story> stories = storyRepository.findByTitleOrDescription(searchText);
        return stories.stream()
                .map(storyMapper::convertStoryToStoryResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean isStoryTitleExists(String storyTitle) {
        return storyRepository.isStoryTitleExists(storyTitle).orElse(false);
    }

    @Override
    public List<StoryResponseDTO> getStoriesUnderCharacter(ObjectId characterId) {
        List<Story> stories = storyRepository.findByCharacterId(characterId);
        if (stories.isEmpty()) {
            throw new StoryNotFoundException("No stories found for character with id: " + characterId);
        }
        return stories.stream()
                .map(storyMapper::convertStoryToStoryResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    public Integer incrementLikeCount(ObjectId storyObjectId) {
        Query query = new Query(Criteria.where("_id").is(storyObjectId));
        Update update = new Update().inc("likeCount", 1);
        try {
            Story updatedStory = mongoTemplate.findAndModify(
                    query,
                    update,
                    FindAndModifyOptions.options().returnNew(true), // Return the updated document after modification
                    Story.class
            );

            if (updatedStory != null) {
                return updatedStory.getLikeCount();
            } else {
                throw new StoryNotFoundException("Story not found with id: " + storyObjectId);
            }
        } catch (DataAccessException e) {
            throw new StoryUpdateException("Failed to increment like count due to a database error.", e);
        }
    }



    @Override
    public StoryResponseDTO updateStory(StoryUpdateDTO storyUpdateDTO) {
        Optional<Story> optionalStory = storyRepository.findById(new ObjectId(storyUpdateDTO.getStoryId()));
        if(optionalStory.isEmpty()){
            throw new StoryNotFoundException("Story not found with id: " + storyUpdateDTO.getStoryId());
        }
        Story existingStory = optionalStory.get();
        if (storyUpdateDTO.getTitle() != null) {
            existingStory.setTitle(storyUpdateDTO.getTitle());
        }
        if (storyUpdateDTO.getDescription() != null) {
            existingStory.setDescription(storyUpdateDTO.getDescription());
        }
        try {
            return storyMapper.convertStoryToStoryResponseDTO(storyRepository.save(existingStory))      ;
        } catch (DataAccessException e) {
            throw new StoryUpdateException("Failed to update the story due to a database error.", e);
        }
    }

    @Override
    public Boolean deleteStory(ObjectId storyId) {
        Optional<Story> optionalStory = storyRepository.findById(storyId);
        if (optionalStory.isEmpty()) {
            throw new StoryNotFoundException("Story not found with id: " + storyId);
        }
        try {
            storyRepository.delete(optionalStory.get());
            return true;
        } catch (DataAccessException e) {
            throw new StoryDeleteException("Failed to delete the story due to a database error.", e);
        }
    }

}
