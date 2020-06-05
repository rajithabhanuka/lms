package com.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.model.CustomResponse;
import com.lms.model.User;
import com.lms.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public class CreateuserController {

    @Autowired
    private UserAccountService userAccountService;

    private static final String SUCCESS = "Successfully created!";
    private static final String FAIL = "Failed to create!";
    private static final String MISSING_DATA = "Failed to create! Required data not available.";
    private static final String CREATE_ERROR = "Error occurred when saving Credential!";

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getDashboard() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/createuser", method = RequestMethod.POST, consumes = "application/json")
    public String createuser(@RequestBody String AccountJSON) {
        String response = "{}";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(AccountJSON, User.class);

            if (user.getEmail() != null && user.getPassword() != null) {
                User created = userAccountService.save(user);

                if (created != null) {
                    response = new CustomResponse(SUCCESS, "SUCCESS").toJson();
                } else {
                    response = new CustomResponse(FAIL, "FAIL").toJson();
                }
            } else {
                response = new CustomResponse(MISSING_DATA, "FAIL").toJson();
            }


        } catch (Exception ex) {
            return new CustomResponse(CREATE_ERROR, "FAIL").toJson();
        }

        return response;
    }
}
