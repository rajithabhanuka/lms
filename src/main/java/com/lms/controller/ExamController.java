package com.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExamController {

    @RequestMapping(value = "/createexam", method = RequestMethod.GET)
    public String createexam() {
        return "createexam";
    }
}
