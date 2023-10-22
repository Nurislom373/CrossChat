package org.khasanof.userservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * @author Nurislom
 * @see org.khasanof.userservice.util
 * @since 10/22/2023 10:12 PM
 */
public class JWTUtils {

    public static String secretKey = "qwertyuiop1lkjhgfdsa2zxcvbnm3mnbvcxz4asdfghjkl5poiuytrewq";

    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secretKey.getBytes());
    }

    public static JWTVerifier getVerifier() {
        return JWT.require(getAlgorithm()).build();
    }

}
