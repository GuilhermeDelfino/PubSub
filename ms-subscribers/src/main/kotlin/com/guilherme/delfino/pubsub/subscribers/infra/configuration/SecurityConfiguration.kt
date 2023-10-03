package com.guilherme.delfino.pubsub.subscribers.infra.configuration

import com.guilherme.delfino.pubsub.token.config.JwtConfiguration
import com.guilherme.delfino.pubsub.token.config.TokenFactory
import com.guilherme.delfino.pubsub.token.security.TokenConfiguration
import com.guilherme.delfino.pubsub.token.security.filters.JwtFilterOncePerRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfiguration(
    private val jwtConfiguration: JwtConfiguration,
    tokenFactory: TokenFactory,
): TokenConfiguration(tokenFactory) {

    @Bean
    override fun configureHttp(http: HttpSecurity): DefaultSecurityFilterChain {
        http.addFilterAfter(
            JwtFilterOncePerRequest(jwtConfiguration, tokenFactory),
            UsernamePasswordAuthenticationFilter::class.java
        )
        return super.configureHttp(http)
    }

}