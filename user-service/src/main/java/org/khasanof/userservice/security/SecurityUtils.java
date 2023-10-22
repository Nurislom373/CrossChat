package org.khasanof.userservice.security;

import com.auth0.jwt.JWT;
import io.jsonwebtoken.Jwts;
import org.bouncycastle.pqc.jcajce.provider.bike.BCBIKEPublicKey;
import org.khasanof.userservice.service.dto.AuthRequestDTO;
import org.khasanof.userservice.util.JWTUtils;

import java.util.*;

/**
 * @author Nurislom
 * @see org.khasanof.userservice.security
 * @since 10/22/2023 9:50 PM
 */
public class SecurityUtils {

    private final Long expirationTimeLong = 30L;

    public String generateToken(AuthRequestDTO request) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", Collections.EMPTY_LIST);
        return doGenerateToken(request, new ArrayList<>());
    }

    private String doGenerateToken(AuthRequestDTO request, List<String> roles) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);
        return JWT.create()
                .withClaim("roles", roles)
                .withClaim("createdAt", createdDate)
                .withSubject(request.getUsername())
                .withExpiresAt(expirationDate)
                .sign(JWTUtils.getAlgorithm());
    }

}
