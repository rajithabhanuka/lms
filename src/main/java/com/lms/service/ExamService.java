package com.lms.service;

import com.lms.model.Exam;
import com.lms.model.User;

import java.util.List;

public interface ExamService {

    Exam save(Exam exam);

    List<Exam> getAll();

    void delete(Exam exam);
}
