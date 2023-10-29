package org.khasanof.userservice.security;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.userservice.security
 * @since 10/22/2023 9:50 PM
 */
public class SecurityUtils {

    public static List<GrantedAuthority> extractAuthorityFromClaims(Map<String, Object> claims) {
        return mapRolesToGrantedAuthorities(getRoleFormClaims(claims));
    }

    @SuppressWarnings("unchecked")
    private static Collection<String> getRoleFormClaims(Map<String, Object> claims) {
        LinkedTreeMap<String, List<String>> realmAccess = (LinkedTreeMap<String, List<String>>) claims.get("realm_access");
        return realmAccess.getOrDefault("roles", Collections.EMPTY_LIST);
    }

    private static List<GrantedAuthority> mapRolesToGrantedAuthorities(Collection<String> roles) {
        return roles.stream().filter(role -> role.startsWith("ROLE_"))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

}
