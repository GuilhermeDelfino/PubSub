package com.guilherme.delfino.pubsub.notification.infra.service;

import com.guilherme.delfino.pubsub.notification.application.protocol.HttpClient;
import com.guilherme.delfino.pubsub.notification.domain.service.AuthService;
import com.guilherme.delfino.pubsub.notification.domain.service.CacheService;
import com.guilherme.delfino.pubsub.notification.infra.service.model.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthServiceImpl implements AuthService {

    private final HttpClient httpClient;
    @Value("${ms.url.auth}")
    private String authURL;
    @Value("${ms.auth.username}")
    private String USERNAME;
    @Value("${ms.auth.password}")
    private String PASSWORD;

    private final String JWT_CACHE_KEY = "authToken";

    private final CacheService cacheService;

    @Override
    public String getToken() {
        log.info("Getting Token from MS Auth");
        if(false){
            log.info("Has cacheable token");
            return cacheService.getItem(JWT_CACHE_KEY);
        }
        log.info("Has no cacheable token!");
        log.info("Generating a JWT Token from MS Auth - {}", authURL);
        LoginRequestDto dto = new LoginRequestDto(USERNAME, PASSWORD);
        HttpEntity<LoginRequestDto> request = new HttpEntity<>(dto);
        Integer retries = 0;
        ResponseEntity<Void> response = null;
        try {
            retries++;
            response = executeRequest(request);
        }catch (Exception e){
            retries++;
            if(retries == 3){
                throw e;
            }
            response = executeRequest(request);
        }

        List<String> authorization = response.getHeaders().get("Authorization");
        if(authorization != null && !authorization.isEmpty()){
            String token = authorization.get(0).substring(6).trim();
//            cacheService.setItem(JWT_CACHE_KEY, token);
            return token;
        }
        throw new RuntimeException("Authentication Service is not available");
    }


    private boolean hasCacheableToken(){
        String item = cacheService.getItem(JWT_CACHE_KEY);
        return item != null && !item.equals("");
    }

    public ResponseEntity<Void> executeRequest(HttpEntity<LoginRequestDto> request){
        ResponseEntity<Void> response = httpClient.execute(authURL, HttpMethod.POST, request,  Void.class);
        return response;
    }
}
