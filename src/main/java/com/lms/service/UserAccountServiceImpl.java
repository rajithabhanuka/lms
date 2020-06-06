package com.lms.service;

import com.lms.model.User;
import com.lms.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Created by Bhanuka
 * */

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private MongoTemplate mongoTemplate;

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
}
