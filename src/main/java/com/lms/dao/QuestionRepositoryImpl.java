package com.lms.dao;

/*
 * Created by Bhanuka
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class QuestionRepositoryImpl implements QuestionRepositoryExtended{

    @Autowired
    private MongoTemplate mongoTemplate;
}
