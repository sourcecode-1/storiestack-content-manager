package com.example.ContentManager.mapper;

import com.example.ContentManager.entity.Artist;
import com.example.ContentManager.entity.Character;
import com.example.ContentManager.entity.Story;
import com.example.ContentManager.model.output.ArtistResponseDTO;
import com.example.ContentManager.model.output.CharacterResponseDTO;
import com.example.ContentManager.model.output.StoryResponseDTO;

import org.springframework.stereotype.Component;

@Component
public class StoryMapper {

    public StoryResponseDTO convertStoryToStoryResponseDTO(Story story) {
        StoryResponseDTO dto = new StoryResponseDTO();
        dto.setId(story.getId().toString());
        dto.setTitle(story.getTitle());
        dto.setImages(story.getImages());
        dto.setLikeCount(story.getLikeCount());
        dto.setDescription(story.getDescription());
        dto.setCreationDate(story.getCreationDate());

        if (story.getCharacter() != null) {
            dto.setCharacter(convertCharacterToDto(story.getCharacter()));
        }
        return dto;
    }

    private CharacterResponseDTO convertCharacterToDto(Character character) {
        CharacterResponseDTO characterDTO = new CharacterResponseDTO();
        characterDTO.setId(character.getId().toString());
        characterDTO.setName(character.getName());
        characterDTO.setIcon(character.getIcon());
        characterDTO.setCreationDate(character.getCreationDate());
        characterDTO.setDescription(character.getDescription());

        if (character.getArtist() != null) {
            characterDTO.setArtist(convertArtistToDto(character.getArtist()));
        }
        return characterDTO;
    }

    private ArtistResponseDTO convertArtistToDto(Artist artist) {
        ArtistResponseDTO artistDTO = new ArtistResponseDTO();
        artistDTO.setId(artist.getId().toString());
        artistDTO.setName(artist.getName());
        artistDTO.setEmail(artist.getEmail());
        artistDTO.setDescription(artist.getDescription());
        return artistDTO;
    }
}
