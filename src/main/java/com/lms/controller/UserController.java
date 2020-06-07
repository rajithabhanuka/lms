package com.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.constant.Constants;
import com.lms.model.CustomResponse;
import com.lms.model.Exam;
import com.lms.model.Role;
import com.lms.model.User;
import com.lms.service.ExamService;
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
import java.util.Date;
import java.util.List;

/*
 * Created by Bhanuka
 * */

@Controller
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    //TODO comments should be added
    //TODO error handling should be added

    @Autowired
    private UserService userService;

    @Autowired
    private ExamService examService;

    @RequestMapping(value = "/create_user", method = RequestMethod.GET)
    public String create_user_view() {
        return "create_user";
    }

    @ResponseBody
    @RequestMapping(value = "/create_user", method = RequestMethod.POST, consumes = "application/json")
    public String create_user(@RequestBody String userJson) {

        String response = "{}";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(userJson, User.class);

            if (user.getEmail() != "" || user.getPassword() != "" || user.getName() != "" || !user.getName().isEmpty()) {

                user.setRegtime(new Date().toString());

                User created = userService.save(user);

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

    @RequestMapping(value = "/my_profile", method = RequestMethod.GET)
    public String my_profile_view(HttpServletRequest request, Model model) {

        String user_id = (String) request.getSession().getAttribute("USER_ID");
        List<Exam> exams = null;
        try {
            User user = userService.findById(user_id);
            if (user.getRole().equals(Role.TEACHER)) {
                exams = examService.getExamByTeacher(user_id);
            } else if (user.getRole().equals(Role.STUDENT)) {
                exams = examService.getAssignExamByStudentID(user_id);
            }
            model.addAttribute("user", user);
            model.addAttribute("exams", exams);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "my_profile";
    }

}
