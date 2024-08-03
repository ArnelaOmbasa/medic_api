package com.example.api_medic.core.repository;


import com.example.api_medic.core.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);
    @Query(value="{'$or': [{'email': ?0}, {'username': ?0}]}")
    Optional<User> findByUsernameOrEmail(String username);

}
