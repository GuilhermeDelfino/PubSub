package com.guilherme.delfino.pubsub.token.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.guilherme.delfino.pubsub.token.config.JwtConfiguration;
import com.guilherme.delfino.pubsub.token.config.TokenFactory;
import com.guilherme.delfino.pubsub.token.entities.ApplicationUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class JwtFilterOncePerRequest extends OncePerRequestFilter {

    private final JwtConfiguration jwtConfiguration;
    private final TokenFactory tokenFactory;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Verifing Header . . .");
        String header = getAuthorizationHeader(request);

        if(headerIsInvalid(header)){
            log.info("Header does not have authorization . . .");
            filterChain.doFilter(request, response);
            return;
        }
        String token = getTokenFromHeader(header);
        try{
            Authentication authentication = generateAuthenticationFromToken(token);
            log.info("Setting in context authentication {}", authentication);

            SecurityContextHolderStrategy contextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
            contextHolderStrategy.getContext().setAuthentication(authentication);

            new HttpSessionSecurityContextRepository().saveContext(contextHolderStrategy.getContext(), request, response);
        }catch (Exception e){
            log.error(e.getMessage());
            SecurityContextHolder.clearContext();
        }finally {
            filterChain.doFilter(request, response);
        }
    }

    public Authentication generateAuthenticationFromToken(String token) throws AccessDeniedException {
        log.info("Generating Authentication from token . . .");
        DecodedJWT decodedJWT = tokenFactory.verifyToken(token);
        List<GrantedAuthority> roles = generateAuthoritiesFromToken(decodedJWT);
        if(tokenClaimsInvalid(decodedJWT)){
            log.info("Token claims invalid");
            throw new AccessDeniedException("Token Claims Invalid");
        }
        ApplicationUser user = createUserFromDecodedJwt(decodedJWT);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, roles);
        authenticationToken.setDetails(user);
        return authenticationToken;
    }

    public boolean tokenClaimsInvalid(DecodedJWT decodedJWT){
        List<GrantedAuthority> roles = generateAuthoritiesFromToken(decodedJWT);
        Long userID = decodedJWT.getClaim("userID").asLong();
        String username = decodedJWT.getSubject();
        return roles.size() == 0 || username == null || userID == null;
    }

    public List<GrantedAuthority> generateAuthoritiesFromToken(DecodedJWT decodedJWT){
        return AuthorityUtils.createAuthorityList(decodedJWT.getClaim("ROLES").asList(String.class).stream().toList());
    }

    public ApplicationUser createUserFromDecodedJwt(DecodedJWT decodedJWT){
        Long userID = decodedJWT.getClaim("userID").asLong();
        String username = decodedJWT.getSubject();

        return ApplicationUser.builder()
                .id(userID)
                .username(username)
                .roles(generateAuthoritiesFromToken(decodedJWT).stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")))
                .build();
    }

    public String getAuthorizationHeader(HttpServletRequest request){
        return request.getHeader(jwtConfiguration.getHeader().getHeaderName());
    }

    public boolean headerIsInvalid(String header){
        return header == null || !header.startsWith(jwtConfiguration.getHeader().getPrefix());
    }
    public String getTokenFromHeader(String header){
        return header.replace(jwtConfiguration.getHeader().getPrefix(), "");
    }
}
