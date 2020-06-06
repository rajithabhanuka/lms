package com.lms.dao;

import com.lms.model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/*
 * Created by Bhanuka
 * */

public class ExamRepositoryImpl implements ExamRepositoryExtended {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Exam> getExamByTeacher(String teacherId) {
        Query query = new Query();
        Criteria criteria = new Criteria("teacherId").is(teacherId);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Exam.class);
    }
}
