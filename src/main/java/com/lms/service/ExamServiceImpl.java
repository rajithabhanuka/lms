package com.lms.service;

import com.lms.dao.ExamRepository;
import com.lms.model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Created by Bhanuka
 * */

@Service
public class ExamServiceImpl implements ExamService{

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public List<Exam> getAll() {
        return null;
    }

    @Override
    public void delete(Exam exam) {

    }
}
