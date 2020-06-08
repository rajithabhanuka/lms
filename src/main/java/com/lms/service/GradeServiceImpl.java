package com.lms.service;

/*
 * Created by Bhanuka
 * */

import com.lms.dao.GradeRepository;
import com.lms.model.Grade;
import com.lms.model.Question;
import com.lms.model.QuestionChoices;
import com.lms.model.StudentsAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private QuestionService questionService;

    @Override
    public Grade save(Grade grade) {

        List<Question> questions = questionService.findQuestionByExamId(grade.getExamId());
        List<String> correctScore = new ArrayList<>();

        for (Question question : questions) {

            List<StudentsAnswers> studentsAnswers = grade.getStudentsAnswers();
            for (StudentsAnswers studentsAnswer : studentsAnswers) {

                if (question.getId().equals(studentsAnswer.getQuestionId()) &&
                        question.getExamid().equals(studentsAnswer.getExamId())) {

                    List<QuestionChoices> questionChoices = question.getQuestionChoices();

                    for (QuestionChoices defaultchoices : questionChoices) {

                        if (defaultchoices.getIscorrect()) {
                            if (defaultchoices.getId().equals(studentsAnswer.getChoiceId())) {
                                correctScore.add(studentsAnswer.getQuestionId());
                            }
                        } else {
                            System.out.println("discard choice");
                        }

                    }
                }
            }

        }

        int score = (correctScore.size() / questions.size() * 100);
        grade.setGrade(score + "");

        return gradeRepository.save(grade);
    }

    @Override
    public List<Grade> getAll() {
        return null;
    }

    @Override
    public void delete(Grade grade) {

    }

    @Override
    public Grade findById(String id) {
        return null;
    }
}
