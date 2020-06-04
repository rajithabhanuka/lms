package com.lms.service;

import com.lms.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {
    Account findByEmail(String email);

    Account save(String email, String password);

    Account save(Account account);

    List<Account> getAll();

    void delete(Account account);
}
