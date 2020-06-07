package com.lms.dao;

/*
 * Created by Bhanuka
 * */

import com.lms.model.Exam;
import com.lms.model.StudentsExams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class StudentsExamsRepositoryImpl implements StudentsExamsRepositoryExtended {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<StudentsExams> getAssignExamByStudentID(String studentId) {
        Query query = new Query();
        Criteria criteria = new Criteria("studentId").is(studentId);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, StudentsExams.class);
    }
}
