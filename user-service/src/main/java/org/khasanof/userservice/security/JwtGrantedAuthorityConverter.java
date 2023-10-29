package org.khasanof.userservice.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;

/**
 * @author Nurislom
 * @see org.khasanof.userservice.security
 * @since 10/29/2023 12:01 PM
 */
public class JwtGrantedAuthorityConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        return SecurityUtils.extractAuthorityFromClaims(source.getClaims());
    }

}
