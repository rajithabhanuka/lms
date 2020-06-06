package com.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * Created by Bhanuka
 * */

@Controller
public class ExamController {

    @RequestMapping(value = "/createexam", method = RequestMethod.GET)
    public String createexam_view() {
        return "createexam";
    }

    @RequestMapping(value = "/createexam", method = RequestMethod.POST, consumes = "application/json")
    public String createexam(@RequestBody String ExamJson) {
        return "createexam";
    }
}
