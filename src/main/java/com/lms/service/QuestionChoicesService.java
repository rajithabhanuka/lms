package com.lms.service;

/*
 * Created by Bhanuka
 * */

import com.lms.model.QuestionChoices;

public interface QuestionChoicesService {

    QuestionChoices getChoiceByIdAndExamIdandQuestionId(String id, String examId, String questionId);
}
