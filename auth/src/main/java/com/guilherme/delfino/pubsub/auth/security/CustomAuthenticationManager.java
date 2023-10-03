package com.guilherme.delfino.pubsub.auth.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Configurable
public class CustomAuthenticationManager implements AuthenticationManager {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        log.info("Trying Authenticate with UserDetails Service: {}", auth);
        String username = auth.getName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String password = auth.getCredentials().toString();
        if(passwordEncoder.matches(password, userDetails.getPassword())){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
            authenticationToken.setDetails(userDetails);
            return authenticationToken;
        }
        throw new UsernameNotFoundException("User '{"+username+"}' not not found");
    }
}
