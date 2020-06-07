package com.lms.dao;

import com.lms.model.Role;
import com.lms.model.User;

import java.util.List;

public interface UserRepositoryExtended {

    User userById(String id);

    List<User> getUserByRole(Role role);
}
