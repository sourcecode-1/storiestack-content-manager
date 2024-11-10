package com.example.ContentManager.service;

import com.example.ContentManager.entity.Character;
import com.example.ContentManager.model.input.CharacterDTO;
import com.example.ContentManager.model.input.CharacterUpdateDTO;
import com.example.ContentManager.model.output.CharacterResponseDTO;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface CharacterService {
    Optional<Character> getCharacterById(ObjectId characterId);
    CharacterResponseDTO addCharacterToArtist(CharacterDTO characterDTO);
    Boolean isCharacterNameExists(String characterName);
    List<CharacterResponseDTO> getAllCharacters();
    CharacterResponseDTO updateCharacter(CharacterUpdateDTO characterUpdateDTO);
}
