package com.practice_spring.journal_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.practice_spring.journal_app.entity.User;


public interface UserRepo extends MongoRepository<User, ObjectId>{
    User findByUserName(String username);
}
