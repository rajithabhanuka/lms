package com.lms.dao;

import com.lms.model.Role;
import com.lms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl implements UserRepositoryExtended{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<User> getUserByRole(Role role) {
        Query query = new Query();
        Criteria criteria = new Criteria("role").is(role);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public User userById(String id) {
        return mongoTemplate.findById(id, User.class);
    }
}
