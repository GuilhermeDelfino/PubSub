package com.guilherme.delfino.pubsub.auth.service;

import com.guilherme.delfino.pubsub.token.entities.ApplicationUser;
import com.guilherme.delfino.pubsub.token.repository.UserApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserApplicationRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> user = repository.findByUsername(username);
        if(user.isEmpty()){
            log.error("User not found, username: '{}'", username);
            throw new UsernameNotFoundException("User not found");
        }
        return user.get();
    }
}
