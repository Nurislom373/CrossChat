package org.khasanof.userservice.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.userservice.service.AuthUserService;
import org.khasanof.userservice.service.dto.AuthUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author Nurislom
 * @see org.khasanof.userservice.web.rest
 * @since 10/22/2023 8:08 PM
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class AuthUserController {

    private final AuthUserService service;

    public AuthUserController(AuthUserService service) {
        this.service = service;
    }

    @PostMapping("/auth_user")
    public Mono<ResponseEntity<Void>> create(@RequestBody Mono<AuthUserDTO> mono) {
        return service.create(mono)
                .then(Mono.fromCallable(() -> ResponseEntity.created(URI.create("/api/auth_user"))
                        .build()));

    }

}
