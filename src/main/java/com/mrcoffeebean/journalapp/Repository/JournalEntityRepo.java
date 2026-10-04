package com.mrcoffeebean.journalapp.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.mrcoffeebean.journalapp.entity.JournalEntity;

// Use PascalCase 'MongoRepository' and provide <YourEntity, ID_Type>
public interface JournalEntityRepo extends MongoRepository<JournalEntity, ObjectId> {
    
}
