package com.lms.dao;

/*
 * Created by Bhanuka
 * */

import com.lms.model.StudentsExams;

import java.util.List;

public interface StudentsExamsRepositoryExtended {

    List<StudentsExams> getAssignExamByStudentID(String studentId);

    List<StudentsExams> getFinishedExamByStudentID(String studentId);

    void updateFinishedExam(String examId, String studentId);

}
