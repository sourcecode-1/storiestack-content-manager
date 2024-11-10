package com.example.ContentManager.service;

import com.example.ContentManager.entity.Artist;
import com.example.ContentManager.model.input.ArtistDTO;
import com.example.ContentManager.model.input.ArtistUpdateDTO;
import com.example.ContentManager.model.output.ArtistResponseDTO;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface ArtistService {
    Optional<Artist> getArtistById(ObjectId artistId);
    ArtistResponseDTO addArtist(ArtistDTO artist);
    ArtistResponseDTO updateArtist(ArtistUpdateDTO artistUpdateDTO);
}
