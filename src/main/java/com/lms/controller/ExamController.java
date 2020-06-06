package com.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.model.Exam;
import com.lms.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * Created by Bhanuka
 * */

@Controller
public class ExamController {

    private static final Logger LOGGER = LogManager.getLogger(ExamController.class);

    //TODO comments should be added
    //TODO error handling should be added

    @RequestMapping(value = "/create_exam", method = RequestMethod.GET)
    public String create_exam_view() {
        return "createexam";
    }

    @RequestMapping(value = "/create_exam", method = RequestMethod.POST, consumes = "application/json")
    public String create_exam(@RequestBody String ExamJson) {

        String response = "{}";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Exam exam = objectMapper.readValue(ExamJson, Exam.class);

            if (true){}


        }catch (Exception ex){
            LOGGER.error(ex);
        }

        return "createexam";
    }
}
