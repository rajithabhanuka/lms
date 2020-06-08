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
                                studentsAnswer.setIsRight(true);
                                System.out.println("correct answer found");
                            }
                        } else {
                            studentsAnswer.setIsRight(false);
                            System.out.println("discard choice");
                        }

                    }
                }
            }

        }

        int correct_answer_count = correctScore.size();
        int questions_count = questions.size();
        double score = (double) correct_answer_count / (double) questions_count * 100;
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
