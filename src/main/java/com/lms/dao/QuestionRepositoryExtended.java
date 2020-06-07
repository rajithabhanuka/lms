package com.lms.dao;

/*
 * Created by Bhanuka
 * */

import com.lms.model.Question;

import java.util.List;

public interface QuestionRepositoryExtended {

    List<Question> findQuestionByExamId(String examId);
}
