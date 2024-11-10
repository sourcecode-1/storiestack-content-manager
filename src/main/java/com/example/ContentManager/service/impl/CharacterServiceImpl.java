package com.example.ContentManager.service.impl;

import com.example.ContentManager.entity.Artist;
import com.example.ContentManager.entity.Character;
import com.example.ContentManager.exception.ArtistNotFoundException;
import com.example.ContentManager.exception.CharacterNotFoundException;
import com.example.ContentManager.exception.CharacterSaveException;
import com.example.ContentManager.exception.DuplicateCharacterNameException;
import com.example.ContentManager.mapper.CharacterMapper;
import com.example.ContentManager.model.input.CharacterDTO;
import com.example.ContentManager.model.input.CharacterUpdateDTO;
import com.example.ContentManager.model.output.CharacterResponseDTO;
import com.example.ContentManager.repository.CharacterRepository;
import com.example.ContentManager.service.ArtistService;
import com.example.ContentManager.service.CharacterService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private ArtistService artistService;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    CharacterMapper characterMapper;

    @Override
    @Cacheable(value = "getCharacterById", key = "#characterId")
    public Optional<Character> getCharacterById(ObjectId characterId) {
        return characterRepository.findById(characterId);
    }

    @Override
    public CharacterResponseDTO addCharacterToArtist(CharacterDTO characterDTO) {
        ObjectId artistObjectId = new ObjectId(characterDTO.getArtistId());
        Optional<Artist> artistOptional = artistService.getArtistById(artistObjectId);
        if (artistOptional.isEmpty()) {
            throw new ArtistNotFoundException("Artist not found with id: " + characterDTO.getArtistId());
        }
        if (isCharacterNameExists(characterDTO.getName())) {
            throw new DuplicateCharacterNameException("A character with the name '" + characterDTO.getName() + "' already exists.");
        }
        Artist artist = artistOptional.get();
        Character character = new Character();
        character.setName(characterDTO.getName());
        character.setIcon(characterDTO.getIcon());
        character.setCreationDate(new Date());
        character.setDescription(characterDTO.getDescription());
        character.setArtist(artist);
        try {
            Character savedCharacter = characterRepository.save(character);
            return characterMapper.convertCharToCharResponseDTO(savedCharacter); // Use mapper to convert to DTO
        } catch (DataAccessException e) {
            throw new CharacterSaveException("Failed to save the character due to a database error.", e);
        }
    }

    @Override
    public Boolean isCharacterNameExists(String characterName) {
        return characterRepository.isCharacterNameExists(characterName).orElse(false);
    }

    @Override
    public List<CharacterResponseDTO> getAllCharacters() {
        List<Character> characters = characterRepository.findAll();
        return characters.stream()
                .map(characterMapper::convertCharToCharResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    public CharacterResponseDTO updateCharacter(CharacterUpdateDTO characterUpdateDTO) {
        Optional<Character> optionalCharacter = characterRepository.findById(new ObjectId(characterUpdateDTO.getCharacterId()));
        if (optionalCharacter.isEmpty()) {
            throw new CharacterNotFoundException("Character not found with id: " + characterUpdateDTO.getCharacterId());
        }
        Character existingCharacter = optionalCharacter.get();
        existingCharacter.setDescription(characterUpdateDTO.getDescription());

        return characterMapper.convertCharToCharResponseDTO(characterRepository.save(existingCharacter));
    }
}
