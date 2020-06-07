package com.lms.dao;

/*
 * Created by Bhanuka
 * */

import com.lms.model.StudentsExams;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentsExamsRepository extends MongoRepository<StudentsExams, String>, StudentsExamsRepositoryExtended{
}
