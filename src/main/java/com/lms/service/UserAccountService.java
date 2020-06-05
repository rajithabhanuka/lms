package com.lms.service;

import com.lms.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserAccountService extends UserDetailsService {

    User save(User user);

    List<User> getAll();

    void delete(User user);
}
