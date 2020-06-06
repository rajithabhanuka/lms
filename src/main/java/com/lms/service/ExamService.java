package com.lms.service;

import com.lms.dao.ExamRepositoryExtended;
import com.lms.model.Exam;

import java.util.List;

/*
 * Created by Bhanuka
 * */

public interface ExamService {

    Exam save(Exam exam);

    List<Exam> getAll();

    void delete(Exam exam);

    List<Exam> getExamByTeacher(String teacherId);
}
