package org.khasanof.userservice.config;

import org.khasanof.userservice.security.JwtGrantedAuthorityConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.HttpStatusServerAccessDeniedHandler;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.userservice.config
 * @since 10/22/2023 11:28 PM
 */
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Value("${spring.security.oauth2.client.provider.oidc.issuer-uri}")
    private String issueUri;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        // ...
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .exceptionHandling(exceptionHandlingSpec -> {
                    exceptionHandlingSpec.accessDeniedHandler(new HttpStatusServerAccessDeniedHandler(HttpStatus.FORBIDDEN));
                })
                .authorizeExchange(authorizeExchangeSpec -> {
                    authorizeExchangeSpec.pathMatchers("/api/users/**")
                            .permitAll();
                    authorizeExchangeSpec.anyExchange()
                            .authenticated();
                })
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> {
                    oAuth2ResourceServerSpec.jwt(jwtSpec -> {
                        jwtSpec.jwtAuthenticationConverter(jwtAuthenticationConverter());
                    });
                });

        return http.build();
    }

    @Bean
    public Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(new JwtGrantedAuthorityConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(authenticationConverter);
    }

    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() {
        NimbusReactiveJwtDecoder reactiveJwtDecoder = (NimbusReactiveJwtDecoder) ReactiveJwtDecoders.fromIssuerLocation(issueUri);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issueUri);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer);
        reactiveJwtDecoder.setJwtValidator(withAudience);
        return reactiveJwtDecoder;
    }

}
