package com.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.constant.Constants;
import com.lms.model.*;
import com.lms.service.ExamService;
import com.lms.service.StudentExamsService;
import com.lms.service.UserService;
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
public class StudentExamsController {

    private static final Logger LOGGER = LogManager.getLogger(StudentExamsController.class);

    @Autowired
    private ExamService examService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentExamsService studentExamsService;

    //TODO comments should be added
    //TODO error handling should be added

    @RequestMapping(value = "/assign_exams_to_students", method = RequestMethod.GET)
    public String assign_exams_to_students_view(HttpServletRequest request, Model model) {

        List<User> users = userService.getUserByRole(Role.STUDENT);
        List<Exam> exams = examService.getExamByTeacher((String) request.getSession().getAttribute("USER_ID"));
        model.addAttribute("users", users);
        model.addAttribute("exams", exams);
        return "assign_exams_to_students";
    }

    @ResponseBody
    @RequestMapping(value = "/assign_exams_to_students", method = RequestMethod.POST, consumes = "application/json")
    public String assign_exams_to_students(HttpServletRequest request, @RequestBody String StudentExamJson) {

        String response = "{}";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            StudentsExams studentsExams = objectMapper.readValue(StudentExamJson, StudentsExams.class);

            if (studentsExams.getStudentId() != null || studentsExams.getExamId() != null) {
                StudentsExams created = studentExamsService.save(studentsExams);

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
