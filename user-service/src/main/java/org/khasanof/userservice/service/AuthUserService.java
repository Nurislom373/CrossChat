package org.khasanof.userservice.service;

import org.khasanof.userservice.service.dto.AuthRequestDTO;
import org.khasanof.userservice.service.dto.AuthUserDTO;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.userservice.service
 * @since 10/22/2023 8:40 PM
 */
public interface AuthUserService {

    Mono<Object> getToken(Mono<AuthRequestDTO> dto);

    Mono<Void> create(Mono<AuthUserDTO> dto);

    Mono<Void> update();

}
