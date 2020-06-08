package com.lms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/*
 * Created by Bhanuka
 * */

@Service
public class GradeRepositoryImpl implements GradeRepositoryExtended{

    @Autowired
    private MongoTemplate mongoTemplate;

}
