package com.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.model.CustomResponse;
import com.lms.model.Role;
import com.lms.model.User;
import com.lms.service.UserAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class CreateuserController {

    private static final Logger LOGGER = LogManager.getLogger(CreateuserController.class);


    @Autowired
    private UserAccountService userAccountService;

    private static final String SUCCESS = "Successfully created!";
    private static final String FAIL = "Failed to create!";
    private static final String MISSING_DATA = "Failed to create! Required data not available.";
    private static final String CREATE_ERROR = "Error occurred when saving Credential!";

    @RequestMapping(value = "/createuser", method = RequestMethod.GET)
    public String create() {
        return "createuser";
    }

    @ResponseBody
    @RequestMapping(value = "/createuser", method = RequestMethod.POST, consumes = "application/json")
    public String createuser(@RequestBody String userJson) {
        String response = "{}";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(userJson, User.class);

            if (user.getEmail() != "" || user.getPassword() != "" || user.getName() != "" || !user.getName().isEmpty()) {

                    user.setRegtime(new Date().toString());

                    User created = userAccountService.save(user);

                    if (created != null) {
                        response = new CustomResponse(SUCCESS, "SUCCESS").toJson();
                    } else {
                        response = new CustomResponse(FAIL, "FAIL").toJson();
                    }
                }else {
                    response = new CustomResponse(MISSING_DATA, "FAIL").toJson();
                }

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return new CustomResponse(CREATE_ERROR, "FAIL").toJson();
        }

        return response;
    }
}
