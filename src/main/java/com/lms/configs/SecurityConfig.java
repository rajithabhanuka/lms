package com.lms.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/student_home").setViewName("student_home");
        registry.addViewController("/teacher_home").setViewName("teacher_home");
        registry.addViewController("/create").setViewName("create_user");
        registry.addViewController("/login").setViewName("login");
    }

}
