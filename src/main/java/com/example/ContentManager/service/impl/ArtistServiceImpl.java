package com.example.ContentManager.service.impl;

import com.example.ContentManager.entity.Artist;
import com.example.ContentManager.exception.ArtistNotFoundException;
import com.example.ContentManager.exception.ArtistSaveException;
import com.example.ContentManager.exception.ArtistUpdateException;
import com.example.ContentManager.mapper.ArtistMapper;
import com.example.ContentManager.model.input.ArtistDTO;
import com.example.ContentManager.model.input.ArtistUpdateDTO;
import com.example.ContentManager.model.output.ArtistResponseDTO;
import com.example.ContentManager.repository.ArtistRepository;
import com.example.ContentManager.service.ArtistService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistMapper artistMapper;

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    @Cacheable(value = "getArtistById", key = "#artistId")
    public Optional<Artist> getArtistById(ObjectId artistId) {
        return artistRepository.findById(artistId);
    }

    @Override
    public ArtistResponseDTO addArtist(ArtistDTO artistDTO) {
        Artist artist = new Artist();
        artist.setName(artistDTO.getName());
        artist.setEmail(artistDTO.getEmail());
        artist.setDescription(artistDTO.getDescription());

        try {
            Artist savedArtist = artistRepository.save(artist);
            return artistMapper.convertArtistToArtistResponseDTO(savedArtist); // Use mapper to convert to DTO
        } catch (DataAccessException e) {
            throw new ArtistSaveException("Failed to save the artist due to a database error.", e);
        }
    }


    @Override
    public ArtistResponseDTO updateArtist(ArtistUpdateDTO artistUpdateDTO) {
        Optional<Artist> optionalArtist = artistRepository.findById(new ObjectId(artistUpdateDTO.getArtistId()));
        if (optionalArtist.isEmpty()) {
            throw new ArtistNotFoundException("Artist not found with id: " + artistUpdateDTO.getArtistId());
        }

        Artist existingArtist = optionalArtist.get();
        if (StringUtils.hasText(artistUpdateDTO.getName())) {
            existingArtist.setName(artistUpdateDTO.getName());
        }
        if (StringUtils.hasText(artistUpdateDTO.getDescription())) {
            existingArtist.setDescription(artistUpdateDTO.getDescription());
        }

        try {
            Artist savedArtist = artistRepository.save(existingArtist);
            return artistMapper.convertArtistToArtistResponseDTO(savedArtist);
        } catch (DataAccessException e) {
            throw new ArtistUpdateException("Failed to save the updated artist details due to database issue.", e);
        }
    }
}
