package com.lms.dao;

import com.lms.model.Exam;

import java.util.List;

/*
 * Created by Bhanuka
 * */

public interface ExamRepositoryExtended {

    List<Exam> getExamByTeacher(String teacherId);

    List<Exam> getAssignExamByStudentID(String studentId);
}
