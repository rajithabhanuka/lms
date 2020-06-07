package com.lms.dao;

import com.lms.model.Role;
import com.lms.model.User;

import java.util.List;

public interface UserRepositoryExtended {

    List<User> getUserByRole(Role role);
}
