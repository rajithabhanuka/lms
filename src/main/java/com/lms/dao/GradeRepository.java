package com.lms.dao;

import com.lms.model.Grade;
import org.springframework.data.mongodb.repository.MongoRepository;

/*
 * Created by Bhanuka
 * */

public interface GradeRepository extends MongoRepository<Grade, String>, GradeRepositoryExtended {
}
