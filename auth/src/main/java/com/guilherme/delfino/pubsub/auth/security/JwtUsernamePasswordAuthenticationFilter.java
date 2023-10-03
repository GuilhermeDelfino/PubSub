package com.guilherme.delfino.pubsub.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guilherme.delfino.pubsub.token.entities.ApplicationUser;
import com.guilherme.delfino.pubsub.token.config.JwtConfiguration;
import com.guilherme.delfino.pubsub.token.config.TokenFactory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtConfiguration jwtConfiguration;
    private final TokenFactory tokenFactory;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("Attempting Authentication . . .");
        ApplicationUser applicationUser = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);

        if(applicationUser == null){
            throw new UsernameNotFoundException("Unable to find user");
        }

        log.info("Creating authentication object and calling loadByUsername in UserDetailService");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(applicationUser.getUsername(), applicationUser.getPassword(), applicationUser.getAuthorities());
        authenticationToken.setDetails(applicationUser);

        return authenticationManager.authenticate(authenticationToken);
    }

    @SneakyThrows
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) {
        log.info("Authentication successful for the user '{}'", auth.getName());

        String jwt = tokenFactory.createJwt(auth);

        response.setHeader("Access-Control-Expose-Header", "XSRF-TOKEN, "+ jwtConfiguration.getHeader().getHeaderName());
        response.setHeader(jwtConfiguration.getHeader().getHeaderName() , jwtConfiguration.getHeader().getPrefix() + jwt);
    }

}
