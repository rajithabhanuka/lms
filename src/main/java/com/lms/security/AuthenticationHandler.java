package com.lms.security;

import com.lms.model.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (Role.STUDENT.getRole().equals(auth.getAuthority())) {

                httpServletResponse.sendRedirect("/");
            }
        }
    }
}
