package com.lms.dao;

import com.lms.model.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Created by Bhanuka
 * */

@Service
public class GradeRepositoryImpl implements GradeRepositoryExtended{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Grade> getGradeByStudentAndExam(String studentId, String examId) {

        Query query = new Query();
        Criteria criteria = new Criteria("studentId").is(studentId);
        Criteria criteria1 = new Criteria("examId").is(examId);
        query.addCriteria(criteria).addCriteria(criteria1);
        return mongoTemplate.find(query, Grade.class);
    }
}
