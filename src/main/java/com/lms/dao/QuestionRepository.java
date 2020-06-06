package com.lms.dao;

import com.lms.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

/*
 * Created by Bhanuka
 * */

public interface QuestionRepository extends MongoRepository<Question, String>, QuestionRepositoryExtended{
}
