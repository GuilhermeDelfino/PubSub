package com.guilherme.delfino.pubsub.token.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt.config")
@Getter
@Setter
public class JwtConfiguration {
    private String secret = "UJYPlu3iZwiIWU0qHId2qa8DdXbRpaBu";
    private String loginUrl = "/api/v1/auth/**";
    private String issuer = "guilherme.delfino@gmail.com";

    private long expiration = 60 * 60; // 1h

    private Header header = Header.JWT;

    @Getter
    public enum Header {
        JWT("signed", "Authorization", "Bearer ");

        private final String type;
        private final String headerName;
        private final String prefix;

        Header(String type, String headerName, String prefix) {
            this.type = type;
            this.headerName = headerName;
            this.prefix = prefix;
        }
    }
}
