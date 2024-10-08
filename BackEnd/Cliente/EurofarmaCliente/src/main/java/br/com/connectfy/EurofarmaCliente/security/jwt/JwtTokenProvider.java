package br.com.connectfy.EurofarmaCliente.security.jwt;


import br.com.connectfy.EurofarmaCliente.dtos.security.TokenVO;
import br.com.connectfy.EurofarmaCliente.exceptions.InvalidJwtAuthenticationException;
import br.com.connectfy.EurofarmaCliente.services.EmployeeService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds;

    @Autowired
    private EmployeeService userDetailsService;

    private Algorithm algorithm;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenVO createToken(Long id, String username, String name, List<String> roles, Long instructorId) {
        Instant now = Instant.now();
        Instant expiration = now.plusMillis(validityInMilliseconds);
        Date expirationDate = Date.from(expiration);
        Date nowDate = Date.from(now);
        String accessToken = getAccessToken(username, roles, now, expirationDate);
        String refreshToken = getRefreshToken(username, roles, now);
        return new TokenVO(id,username, name,roles, true, now, expiration, accessToken, refreshToken, instructorId);
    }

    public TokenVO refreshToken(String refreshToken) {
        if (refreshToken.startsWith("Bearer ")) {
            refreshToken = refreshToken.substring("Bearer ".length());
        }
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String username = decodedJWT.getSubject();
        String name = decodedJWT.getClaim("name").asString();
        Long id = decodedJWT.getClaim("id").asLong();
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        Long instructorId = decodedJWT.getClaim("instructorId").asLong();
        return createToken(id,username, name, roles, instructorId);
    }

    private String getAccessToken(String username, List<String> roles, Instant now, Date expiration) {
        String issueUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .withSubject(username)
                .withIssuer(issueUrl)
                .sign(algorithm);
    }

    private String getRefreshToken(String username, List<String> roles, Instant now) {
        Instant validityRefreshToken =  now.plusMillis (validityInMilliseconds * 3);
        String issueUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validityRefreshToken)
                .withSubject(username)
                .withIssuer(issueUrl)
                .sign(algorithm);
    }

    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "Invalidas:", userDetails.getAuthorities());
    }

    private DecodedJWT decodedToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (Exception e) {
            throw new InvalidJwtAuthenticationException("Invalid JWT token");
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            DecodedJWT decodedJWT = decodedToken(token);
            return !decodedJWT.getExpiresAt().before(new Date());
        } catch (Exception e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }
}
