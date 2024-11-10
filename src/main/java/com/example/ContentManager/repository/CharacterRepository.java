package com.example.ContentManager.repository;

import com.example.ContentManager.entity.Character;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CharacterRepository extends MongoRepository<Character, ObjectId> {

    @Query(value = "{ 'name': { $regex: ?0, $options: 'i' } }", exists = true)
    Optional<Boolean> isCharacterNameExists(String name);

}
