package com.example.ContentManager.controller;

import com.example.ContentManager.model.input.*;
import com.example.ContentManager.model.output.APIResponse;
import com.example.ContentManager.model.output.ArtistResponseDTO;
import com.example.ContentManager.model.output.CharacterResponseDTO;
import com.example.ContentManager.model.output.StoryResponseDTO;
import com.example.ContentManager.service.ArtistService;
import com.example.ContentManager.service.CharacterService;
import com.example.ContentManager.service.StoryService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ContentManager.util.AppConstants.*;
import static com.example.ContentManager.util.AppUtils.isValidId;

@RestController
public class AppController {

    @Autowired
    private ArtistService artistService;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private StoryService storyService;

    @GetMapping("/")
    public String home() {
        return HOME_MSG;
    }

    //CREATE ENDPOINTS
    @PostMapping("/addArtist")
    public ResponseEntity<APIResponse<ArtistResponseDTO>> addArtist(@Valid @RequestBody ArtistDTO artist) {
        ArtistResponseDTO createdArtist = artistService.addArtist(artist);
        APIResponse<ArtistResponseDTO> response = new APIResponse<>(createdArtist, ADD_ARTIST_OK_MSG, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/addCharacterToArtist")
    public ResponseEntity<APIResponse<CharacterResponseDTO>> addCharacterToArtist(@Valid @RequestBody CharacterDTO character) {
        CharacterResponseDTO createdCharacter = characterService.addCharacterToArtist(character);
        APIResponse<CharacterResponseDTO> response = new APIResponse<>(createdCharacter, ADD_CHAR_TO_ARTIST_OK_MSG, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/addStoryToCharacter")
    public ResponseEntity<APIResponse<StoryResponseDTO>> addStoryToCharacter(@Valid @RequestBody StoryDTO story) {
        StoryResponseDTO createdStory = storyService.addStoryToCharacter(story);
        APIResponse<StoryResponseDTO> response = new APIResponse<>(createdStory, ADD_STORY_TO_CHAR_OK_MSG, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    //READ ENDPOINTS
    @GetMapping("/searchStories/{searchTerm}")
    public ResponseEntity<APIResponse<List<StoryResponseDTO>>> searchStories(@PathVariable String searchTerm) {
        if (!StringUtils.hasText(searchTerm)) {
            return ResponseEntity.badRequest()
                    .body(new APIResponse<>(null, SEARCH_STORIES_BAD_REQ_MSG, HttpStatus.BAD_REQUEST.value()));
        }
        List<StoryResponseDTO> stories = storyService.searchStories(searchTerm);
        return ResponseEntity.ok()
                .body(new APIResponse<>(stories, SEARCH_STORIES_OK_MSG, HttpStatus.OK.value()));
    }


    @GetMapping("/isCharacterNameExists/{characterName}")
    public ResponseEntity<APIResponse<Boolean>> isCharacterNameExists(@PathVariable String characterName) {
        if (!StringUtils.hasText(characterName)) {
            return ResponseEntity.badRequest()
                    .body(new APIResponse<>(null, CHAR_NAME_EXIST_BAD_REQ_MSG, HttpStatus.BAD_REQUEST.value()));
        }
        boolean exists = characterService.isCharacterNameExists(characterName);
        return ResponseEntity.ok()
                .body(new APIResponse<>(exists, CHAR_NAME_EXIST_OK_MSG, HttpStatus.OK.value()));
    }


    @GetMapping("/isStoryTitleExists/{storyTitle}")
    public ResponseEntity<APIResponse<Boolean>> isStoryTitleExists(@PathVariable String storyTitle) {
        if (!StringUtils.hasText(storyTitle)) {
            return ResponseEntity.badRequest()
                    .body(new APIResponse<>(null, STORY_TITLE_EXIST_BAD_REQ_MSG, HttpStatus.BAD_REQUEST.value()));
        }
        boolean exists = storyService.isStoryTitleExists(storyTitle);
        return ResponseEntity.ok()
                .body(new APIResponse<>(exists, STORY_TITLE_EXIST_OK_MSG, HttpStatus.OK.value()));
    }


    @GetMapping("/getAllCharacters") // Char + Artist
    public ResponseEntity<APIResponse<List<CharacterResponseDTO>>> getAllCharacters() {
        List<CharacterResponseDTO> characters = characterService.getAllCharacters();
        return ResponseEntity.ok()
                .body(new APIResponse<>(characters, CHARS_RETRIEVED_OK_MSG, HttpStatus.OK.value()));
    }


    @GetMapping("/getStoriesUnderCharacter/{characterId}")
    public ResponseEntity<APIResponse<List<StoryResponseDTO>>> getStoriesUnderCharacter(@PathVariable String characterId) {
        if (!isValidId(characterId)) {
            return ResponseEntity.badRequest()
                    .body(new APIResponse<>(null, STORIES_UNDER_CHAR_BAD_REQ_MSG, HttpStatus.BAD_REQUEST.value()));
        }
        ObjectId characterObjectId = new ObjectId(characterId);
        List<StoryResponseDTO> stories = storyService.getStoriesUnderCharacter(characterObjectId);
        return ResponseEntity.ok()
                .body(new APIResponse<>(stories, STORIES_UNDER_CHAR_OK_MSG, HttpStatus.OK.value()));
    }

    //UPDATE ENDPOINTS
    @PutMapping("/incrementLikeCount/{storyId}")
    public ResponseEntity<APIResponse<Integer>> incrementLikeCount(@PathVariable String storyId) {
        if (!isValidId(storyId)) {
            return ResponseEntity.badRequest()
                    .body(new APIResponse<>(null, INC_LIKE_COUNT_BAD_REQ_MSG, HttpStatus.BAD_REQUEST.value()));
        }

        ObjectId storyObjectId = new ObjectId(storyId);
        Integer updatedLikeCount = storyService.incrementLikeCount(storyObjectId);
        return ResponseEntity.ok()
                .body(new APIResponse<>(updatedLikeCount, INC_LIKE_COUNT_OK_MSG, HttpStatus.OK.value()));
    }


    @PutMapping("/updateArtist")
    public ResponseEntity<APIResponse<ArtistResponseDTO>> updateArtist(@Valid @RequestBody ArtistUpdateDTO artistUpdateDTO) {
        ArtistResponseDTO updatedArtist = artistService.updateArtist(artistUpdateDTO);
        return ResponseEntity.ok()
                .body(new APIResponse<>(updatedArtist, UPDATE_ARTIST_OK_MSG, HttpStatus.OK.value()));
    }

    @PutMapping("/updateCharacter")
    public ResponseEntity<APIResponse<CharacterResponseDTO>> updateCharacter(@Valid @RequestBody CharacterUpdateDTO characterUpdateDTO) {
        CharacterResponseDTO updatedCharacter = characterService.updateCharacter(characterUpdateDTO);
        return ResponseEntity.ok()
                .body(new APIResponse<>(updatedCharacter, UPDATE_CHARACTER_OK_MSG, HttpStatus.OK.value()));
    }

    @PutMapping("/updateStory")
    public ResponseEntity<APIResponse<StoryResponseDTO>> updateStory(@Valid @RequestBody StoryUpdateDTO storyUpdateDTO) {
        StoryResponseDTO updatedStory = storyService.updateStory(storyUpdateDTO);
        return ResponseEntity.ok(new APIResponse<>(updatedStory, UPDATE_STORY_OK_MSG, HttpStatus.OK.value()));
    }

    @DeleteMapping("/deleteStory/{storyId}")
    public ResponseEntity<APIResponse<String>> deleteStory(@PathVariable String storyId) {
        if (!isValidId(storyId)) {
            return ResponseEntity.badRequest()
                    .body(new APIResponse<>(null, DELETE_STORY_BAD_REQ_MSG, HttpStatus.BAD_REQUEST.value()));
        }

        ObjectId storyObjectId = new ObjectId(storyId);
        storyService.deleteStory(storyObjectId);
        return ResponseEntity.ok()
                .body(new APIResponse<>(null, DELETE_STORY_OK_MSG, HttpStatus.OK.value()));
    }

}

