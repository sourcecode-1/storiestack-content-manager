package com.example.ContentManager.repository;

import com.example.ContentManager.entity.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.bson.types.ObjectId;

public interface ArtistRepository extends MongoRepository<Artist, ObjectId> {}