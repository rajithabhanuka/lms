package com.lms.dao;

/*
 * Created by Bhanuka
 * */

import com.lms.model.QuestionChoices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class QuestionChoicesRepositoryImpl implements QuestionChoicesRepositoryExtended{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public QuestionChoices getChoiceByIdAndExamIdandQuestionId(String id, String examId, String questionId) {
        Query query = new Query();
        Criteria criteria = new Criteria("id").is(id);
        Criteria criteria1 = new Criteria("examid").is(examId);
        Criteria criteria2 = new Criteria("questionid").is(questionId);
        query.addCriteria(criteria).addCriteria(criteria1).addCriteria(criteria2);
        return mongoTemplate.find(query, QuestionChoices.class).get(0);

    }
}
