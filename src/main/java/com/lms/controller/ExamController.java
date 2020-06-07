package com.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.constant.Constants;
import com.lms.model.*;
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
import java.util.Date;
import java.util.List;

/*
 * Created by Bhanuka
 * */

@Controller
public class ExamController {

    private static final Logger LOGGER = LogManager.getLogger(ExamController.class);

    @Autowired
    private ExamService examService;

    @Autowired
    private QuestionService questionService;

    //TODO comments should be added
    //TODO error handling should be added

    @RequestMapping(value = "/create_exam", method = RequestMethod.GET)
    public String create_exam_view(HttpServletRequest request, Model model) {
        return "create_exam";
    }

    @ResponseBody
    @RequestMapping(value = "/create_exam", method = RequestMethod.POST, consumes = "application/json")
    public String create_exam(HttpServletRequest request, @RequestBody String ExamJson) {

        String response = "{}";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Exam exam = objectMapper.readValue(ExamJson, Exam.class);

            if (!exam.getExamname().isEmpty() || !exam.getExamname().equals("")) {

                exam.setCreatedDate(new Date());

                Exam created = examService.save(exam);

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

    @RequestMapping(value = "/launch_exam", method = RequestMethod.POST)
    public String launch_exam_view(HttpServletRequest request, Model model) {
        try {
            String selected_exam_id = request.getParameter("selected_exam_id");
            Exam exam = examService.findById(selected_exam_id);
            List<Question> questions = questionService.findQuestionByExamId(exam.getId());
            model.addAttribute("exam", exam);
            model.addAttribute("questions", questions);
            request.getSession().setAttribute("questions", questions);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return "launch_exam";
    }


    @RequestMapping(value = "/launch_exam_progress", method = RequestMethod.GET)
    public String launch_exam_progress(HttpServletRequest request, Model model) {
        try {

            int currentQuestionID = 0;

            List<Question> questions = (List<Question>) request.getSession().getAttribute("questions");

            Object object = request.getSession().getAttribute("currentQuestionID");

            if (object == null) {
                currentQuestionID = 0;
            } else {
                currentQuestionID = (Integer) request.getSession().getAttribute("currentQuestionID");
            }

            String add = request.getParameter("add");
            String deduct = request.getParameter("deduct");

            if (add != null && object != null && add.equals("1") && currentQuestionID < questions.size() - 1) {
                currentQuestionID += 1;
            }

            if (deduct != null && object != null && deduct.equals("1") && currentQuestionID > 0) {
                currentQuestionID -= 1;
            }

            request.getSession().setAttribute("currentQuestionID", currentQuestionID);
            model.addAttribute("currentQuestionID", currentQuestionID);
            model.addAttribute("question", questions.get(currentQuestionID));

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return "launch_exam_progress";
    }


}
