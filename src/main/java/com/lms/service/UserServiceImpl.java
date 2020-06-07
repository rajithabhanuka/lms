package com.lms.service;

import com.lms.model.Role;
import com.lms.model.User;
import com.lms.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/*
 * Created by Bhanuka
 * */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (email != null) {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException(email);
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(), getUserAuthority(user));
        }
        return null;
    }

    private List<GrantedAuthority> getUserAuthority(User user) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new ArrayList<>(roles);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void delete(User account) {

    }

    @Override
    public List<User> getUserByRole(Role role) {
        return userRepository.getUserByRole(role);
    }

    @Override
    public User findById(String id) {
        return userRepository.userById(id);
    }
}
