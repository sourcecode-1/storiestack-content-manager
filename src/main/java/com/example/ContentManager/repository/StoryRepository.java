package com.example.ContentManager.repository;

import com.example.ContentManager.entity.Story;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoryRepository extends MongoRepository<Story, ObjectId> {

    @Query("{ '$or': [ { 'title': { $regex: ?0, $options: 'i' } }, { 'description': { $regex: ?0, $options: 'i' } } ] }")
    List<Story> findByTitleOrDescription(String searchText);

    @Query(value = "{ 'title': { $regex: ?0, $options: 'i' } }", exists = true)
    Optional<Boolean> isStoryTitleExists(String storyTile);

    @Query("{ 'character.$id': ?0 }")
    List<Story> findByCharacterId(ObjectId characterId);
}

