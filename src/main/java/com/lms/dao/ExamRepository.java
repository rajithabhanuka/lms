package com.lms.dao;

import com.lms.model.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;

/*
 * Created by Bhanuka
 * */

public interface ExamRepository extends MongoRepository<Exam, String> , ExamRepositoryExtended {
}
