@file:JvmName("SecurityUtils")

package org.khasanof.util

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import reactor.core.publisher.Mono

fun getCurrentUserLogin(): Mono<String> = ReactiveSecurityContextHolder.getContext()
    .map(SecurityContext::getAuthentication)
    .flatMap { Mono.justOrEmpty(extractPrincipal(it)) }

fun extractPrincipal(authentication: Authentication?): String? {

    if (authentication == null) {
        return null
    }

    return when (val principal = authentication.principal) {
        is UserDetails -> principal.username
        is JwtAuthenticationToken -> (authentication as JwtAuthenticationToken).token.claims as String
        is DefaultOidcUser -> {
            if (principal.attributes.containsKey("preferred_username")) {
                principal.attributes["preferred_username"].toString()
            } else {
                null
            }
        }
        is String -> principal
        else -> null
    }
}

/**
 * Check if a user is authenticated.
 *
 * @return true if the user is authenticated, false otherwise.
 */
fun isAuthenticated(): Mono<Boolean> {
    return ReactiveSecurityContextHolder.getContext()
        .map(SecurityContext::getAuthentication)
        .map(Authentication::getAuthorities)
        .map {
            it
                .map(GrantedAuthority::getAuthority)
                .none { it == "ANONYMOUS" }
        }
}
