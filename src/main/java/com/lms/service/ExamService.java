package com.lms.service;

import com.lms.dao.ExamRepositoryExtended;
import com.lms.model.Exam;
import com.lms.model.User;

import java.util.List;

/*
 * Created by Bhanuka
 * */

public interface ExamService {

    Exam save(Exam exam);

    List<Exam> getAll();

    void delete(Exam exam);

    Exam findById(String id);

    List<Exam> getExamByTeacher(String teacherId);

    List<Exam> getAssignExamByStudentID(String studentId);

    List<Exam> getFinishedExamByStudentID(String studentId);

}
