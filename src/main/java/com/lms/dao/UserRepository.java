package com.lms.dao;

import com.lms.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/*
 * Created by Bhanuka
 * */

public interface UserRepository extends MongoRepository<User, String> , UserRepositoryExtended {

    @Query(value = "{email: ?0}")
    User findByEmail(String email);
}
