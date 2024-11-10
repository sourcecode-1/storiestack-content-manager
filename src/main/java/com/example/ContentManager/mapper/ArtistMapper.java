package com.example.ContentManager.mapper;

import com.example.ContentManager.entity.Artist;
import com.example.ContentManager.model.output.ArtistResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper {

    public ArtistResponseDTO convertArtistToArtistResponseDTO(Artist artist) {
        ArtistResponseDTO dto = new ArtistResponseDTO();
        dto.setId(artist.getId());
        dto.setName(artist.getName());
        dto.setEmail(artist.getEmail());
        dto.setDescription(artist.getDescription());
        return dto;
    }
}

