package com.mrcoffeebean.journalapp.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.mrcoffeebean.journalapp.entity.UserEntity;

public interface UserEntityRepo extends MongoRepository<UserEntity, ObjectId> {

    UserEntity findByUserName(String userName);

    void deleteByUserName(String userName);
}