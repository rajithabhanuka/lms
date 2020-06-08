package com.lms.dao;

/*
 * Created by Bhanuka
 * */

import com.lms.model.Grade;

import java.util.List;

public interface GradeRepositoryExtended {

    List<Grade> getGradeByStudentAndExam(String studentId, String examId);

}
