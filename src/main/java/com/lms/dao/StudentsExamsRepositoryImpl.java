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
import org.springframework.data.mongodb.core.query.Update;

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

    @Override
    public void updateFinishedExam(String examId, String studentId) {
        try {
            Query query = new Query();
            Criteria criteria = new Criteria("studentId").is(studentId);
            Criteria criteria1 = new Criteria("examId").is(examId);
            query.addCriteria(criteria).addCriteria(criteria1);

            Update update = new Update();

            //TODO this time only one attempt
            update.set("attempts", 1);

            mongoTemplate.updateFirst(query, update, StudentsExams.class);
        } catch (Exception ex) {
            //TODO logger
            System.out.println("error ----");
        }

    }

    @Override
    public List<StudentsExams> getFinishedExamByStudentID(String studentId) {
        Query query = new Query();
        Criteria criteria = new Criteria("studentId").is(studentId);
        //TODO attempts greater than one only
        Criteria criteria1 = new Criteria("attempts").gte(1);
        query.addCriteria(criteria).addCriteria(criteria1);
        return mongoTemplate.find(query, StudentsExams.class);
    }
}
