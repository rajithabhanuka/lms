package com.lms.dao;

/*
 * Created by Bhanuka
 * */

import com.lms.model.QuestionChoices;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionChoicesRepository extends MongoRepository<QuestionChoices, String>, QuestionChoicesRepositoryExtended {
}
