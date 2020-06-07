package com.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.constant.Constants;
import com.lms.model.CustomResponse;
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

/*
 * Created by Bhanuka
 * */

@Controller
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    //TODO comments should be added
    //TODO error handling should be added

    @Autowired
    private UserAccountService userAccountService;

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

                    User created = userAccountService.save(user);

                    if (created != null) {
                        response = new CustomResponse(Constants.SUCCESS, "SUCCESS").toJson();
                    } else {
                        response = new CustomResponse(Constants.FAIL, "FAIL").toJson();
                    }
                }else {
                    response = new CustomResponse(Constants.MISSING_DATA, "FAIL").toJson();
                }

        } catch (Exception ex) {
            LOGGER.error(ex);
            response = new CustomResponse(Constants.CREATE_ERROR, "FAIL").toJson();
        }

        return response;
    }
}
