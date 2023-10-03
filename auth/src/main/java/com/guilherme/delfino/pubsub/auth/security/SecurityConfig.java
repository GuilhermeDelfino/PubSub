package com.guilherme.delfino.pubsub.auth.security;

import com.guilherme.delfino.pubsub.token.config.JwtConfiguration;
import com.guilherme.delfino.pubsub.token.config.TokenFactory;
import com.guilherme.delfino.pubsub.token.security.TokenConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends TokenConfiguration {

    private final UserDetailsService userDetailsService;
    private final JwtConfiguration jwtConfiguration;

    public SecurityConfig(JwtConfiguration jwtConfiguration, UserDetailsService userDetailsService, TokenFactory tokenFactory) {
        super(tokenFactory);
        this.userDetailsService = userDetailsService;
        this.jwtConfiguration = jwtConfiguration;
    }

    @Bean
    public DefaultSecurityFilterChain configureHttp(HttpSecurity http) throws Exception {
        http.addFilter(new JwtUsernamePasswordAuthenticationFilter(authManager(), jwtConfiguration, tokenFactory));
        return super.configureHttp(http);
    }
    @Bean
    @Primary
    public AuthenticationManager authManager(){
        return new CustomAuthenticationManager(encoder(), userDetailsService);
    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
