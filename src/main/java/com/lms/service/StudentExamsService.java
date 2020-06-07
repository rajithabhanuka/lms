package com.lms.service;

import com.lms.model.StudentsExams;

import java.util.List;

/*
 * Created by Bhanuka
 * */

public interface StudentExamsService {

    StudentsExams save(StudentsExams studentsExams);

    List<StudentsExams> getAll();

    void delete(StudentsExams studentsExams);

}
