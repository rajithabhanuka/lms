package com.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.constant.Constants;
import com.lms.model.CustomResponse;
import com.lms.model.Exam;
import com.lms.model.User;
import com.lms.service.ExamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
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

    //TODO comments should be added
    //TODO error handling should be added

    @RequestMapping(value = "/create_exam", method = RequestMethod.GET)
    public String create_exam_view() {
        return "createexam";
    }

    @ResponseBody
    @RequestMapping(value = "/create_exam", method = RequestMethod.POST, consumes = "application/json")
    public String create_exam(@RequestBody String ExamJson) {

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

    @RequestMapping(value = "/assign_quizz", method = RequestMethod.GET)
    public String assign_quizz_view(HttpServletRequest request, Model model) {

        List<Exam> exams = examService.getExamByTeacher((String) request.getSession().getAttribute("USER_ID"));
        model.addAttribute("exams", exams);
        return "assign_quizz";
    }
}
