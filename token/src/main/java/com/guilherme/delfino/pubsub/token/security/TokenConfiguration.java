package com.guilherme.delfino.pubsub.token.security;

import com.guilherme.delfino.pubsub.token.config.JwtConfiguration;
import com.guilherme.delfino.pubsub.token.config.TokenFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor
@Slf4j
public class TokenConfiguration {
    protected final TokenFactory tokenFactory;
    private final List<String> ORIGIN_PATTERNS = List.of(
//            "https://localhost:3333" // BFF-SITE LOCAL
            "http://localhost:8080", // API GATEWAY LOCAL
            "http://localhost:8083" // MS NOTIFICATION
    );

    private final AntPathRequestMatcher[] PUBLIC_URLS = {
            new AntPathRequestMatcher("**/actuator/**"),
            new AntPathRequestMatcher("**/h2-console/**"),
            new AntPathRequestMatcher("/login","POST"),
            new AntPathRequestMatcher("/api/v1/auth", "POST"),
            new AntPathRequestMatcher("/api/v1/events/docs/**"),
            new AntPathRequestMatcher("/api/v1/events/swagger-ui/**"),
            new AntPathRequestMatcher("/api/v1/subscribers/docs/**"),
            new AntPathRequestMatcher("/api/v1/subscribers/**"),
    };
    private final AntPathRequestMatcher[] USER_URLS = {
            new AntPathRequestMatcher("/api/v1/events/**", "GET"),
    };
    private final AntPathRequestMatcher[] ADMIN_URLS = {
            new AntPathRequestMatcher("/api/v1/events/**"),
    };
    public DefaultSecurityFilterChain configureHttp(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_URLS).permitAll()
                        .requestMatchers(USER_URLS).hasRole("USER")
                        .requestMatchers(ADMIN_URLS).hasRole("ADMIN")
                        .anyRequest().denyAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .exceptionHandling(customizer -> customizer
                        .authenticationEntryPoint((request, response, ex) -> {
                                    log.info("Exception {}, Class {} ", ex.getMessage(), ex.getClass());
                                    response.setStatus(SC_UNAUTHORIZED); // any error throw 401
                                }
                        )
                )
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ORIGIN_PATTERNS);
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
