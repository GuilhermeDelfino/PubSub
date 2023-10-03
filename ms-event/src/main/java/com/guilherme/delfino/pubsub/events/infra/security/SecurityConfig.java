package com.guilherme.delfino.pubsub.events.infra.security;

import com.guilherme.delfino.pubsub.token.config.JwtConfiguration;
import com.guilherme.delfino.pubsub.token.config.TokenFactory;
import com.guilherme.delfino.pubsub.token.security.TokenConfiguration;
import com.guilherme.delfino.pubsub.token.security.filters.JwtFilterOncePerRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends TokenConfiguration {
    private final JwtConfiguration jwtConfiguration;

    public SecurityConfig(JwtConfiguration jwtConfiguration, TokenFactory tokenFactory) {
        super(tokenFactory);
        this.jwtConfiguration = jwtConfiguration;
    }

    @Override
    @Bean
    public DefaultSecurityFilterChain configureHttp(HttpSecurity http) throws Exception {
        http.addFilterAfter(new JwtFilterOncePerRequest(jwtConfiguration, tokenFactory), UsernamePasswordAuthenticationFilter.class);
        return super.configureHttp(http);
    }
}
