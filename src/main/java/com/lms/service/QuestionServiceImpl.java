package com.lms.service;

import com.lms.dao.QuestionRepository;
import com.lms.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Created by Bhanuka
 * */

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getAll() {
        return null;
    }

    @Override
    public void delete(Question question) {

    }

    @Override
    public List<Question> getQuestionByTeacher(String teacherId) {
        return null;
    }

    @Override
    public List<Question> getQuestionByStudent(String studentId) {
        return null;
    }

    @Override
    public List<Question> findQuestionByExamId(String examId) {
        return questionRepository.findQuestionByExamId(examId);
    }
}
