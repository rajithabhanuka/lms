package com.lms.service;

import com.lms.model.Account;
import com.lms.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public Account findByEmail(String email) {
        return null;
    }

    @Override
    public Account save(String email, String password) {
        return null;
    }

    @Override
    public Account save(Account account) {
        return null;
    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public void delete(Account account) {

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email != null) {
            Account account = new Account();
            account.setId("1");
            account.setEmail("rajithabhanuka1@gmail.com");
            account.setPassword("admin");
            account.setRole(Role.STUDENT);
            //Account account = accountRepository.findByEmail(email);
//            if (account == null) {
//                throw new UsernameNotFoundException(email);
//            }
            return new org.springframework.security.core.userdetails.User("rajithabhanuka1@gmail.com",
                    "admin", getUserAuthority(account));
        }
        return null;
    }

    private List<GrantedAuthority> getUserAuthority(Account account) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(Role.STUDENT.getRole()));
        return new ArrayList<>(roles);
    }
}
