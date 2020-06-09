package com.lms.service;


import com.lms.dao.QuestionChoicesRepository;
import com.lms.dao.QuestionChoicesRepositoryExtended;
import com.lms.model.QuestionChoices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Created by Bhanuka
 * */

@Service
public class QuestionChoicesServiceImpl implements QuestionChoicesService {

    @Autowired
    private QuestionChoicesRepository questionChoicesRepository;

    @Override
    public QuestionChoices getChoiceByIdAndExamIdandQuestionId(String id, String examId, String questionId) {
        return questionChoicesRepository.getChoiceByIdAndExamIdandQuestionId(id, examId, questionId);
    }
}
