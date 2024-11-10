package com.example.ContentManager.mapper;

import com.example.ContentManager.entity.Artist;
import com.example.ContentManager.entity.Character;
import com.example.ContentManager.model.output.ArtistResponseDTO;
import com.example.ContentManager.model.output.CharacterResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {

    public CharacterResponseDTO convertCharToCharResponseDTO(Character character) {
        CharacterResponseDTO dto = new CharacterResponseDTO();
        dto.setId(character.getId().toString());
        dto.setName(character.getName());
        dto.setIcon(character.getIcon());
        dto.setCreationDate(character.getCreationDate());
        dto.setDescription(character.getDescription());

        if (character.getArtist() != null) {
            dto.setArtist(toArtistResponseDto(character.getArtist()));
        }
        return dto;
    }

    private ArtistResponseDTO toArtistResponseDto(Artist artist) {
        ArtistResponseDTO artistDto = new ArtistResponseDTO();
        artistDto.setId(artist.getId().toString());
        artistDto.setName(artist.getName());
        artistDto.setEmail(artist.getEmail());
        artistDto.setDescription(artist.getDescription());
        return artistDto;
    }
}
