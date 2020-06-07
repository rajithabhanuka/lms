package com.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.constant.Constants;
import com.lms.model.CustomResponse;
import com.lms.model.Exam;
import com.lms.model.Question;
import com.lms.service.ExamService;
import com.lms.service.QuestionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * Created by Bhanuka
 * */

@Controller
public class QuestionController {
    private static final Logger LOGGER = LogManager.getLogger(QuestionController.class);

    @Autowired
    private ExamService examService;

    @Autowired
    private QuestionService questionService;

    //TODO comments should be added
    //TODO error handling should be added

    @RequestMapping(value = "/assign_quizz", method = RequestMethod.GET)
    public String assign_quizz_view(HttpServletRequest request, Model model) {

        List<Exam> exams = examService.getExamByTeacher((String) request.getSession().getAttribute("USER_ID"));
        model.addAttribute("exams", exams);
        return "assign_quizz";
    }

    @ResponseBody
    @RequestMapping(value = "/assign_quizz", method = RequestMethod.POST, consumes = "application/json")
    public String assign_quizz(@RequestBody String QuizJson, HttpServletRequest request) {
        String response = "{}";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Question question = objectMapper.readValue(QuizJson, Question.class);

            if (!question.getQuestionChoices().get(0).getChoice().equals("") ||
                    !question.getQuestionChoices().get(1).getChoice().equals("") ||
                    !question.getQuestionChoices().get(2).getChoice().equals("") ||
                    !question.getQuestionChoices().get(3).getChoice().equals("") ||
                    !question.getQuestionChoices().get(4).getChoice().equals("") ||
                    !question.getQuestion().equals("")) {

                Question created = questionService.save(question);

                if (created != null) {
                    response = new CustomResponse(Constants.SUCCESS, "SUCCESS").toJson();
                } else {
                    response = new CustomResponse(Constants.FAIL, "FAIL").toJson();
                }
            } else {
                response = new CustomResponse(Constants.MISSING_DATA, "FAIL").toJson();
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
            response = new CustomResponse(Constants.CREATE_ERROR, "FAIL").toJson();
        }
        return response;
    }
}
