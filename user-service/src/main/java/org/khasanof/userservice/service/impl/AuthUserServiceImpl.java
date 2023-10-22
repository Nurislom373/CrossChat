package org.khasanof.userservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.userservice.domain.AuthUser;
import org.khasanof.userservice.domain.enumeration.Status;
import org.khasanof.userservice.repository.AuthUserRepository;
import org.khasanof.userservice.service.AuthUserService;
import org.khasanof.userservice.service.dto.AuthRequestDTO;
import org.khasanof.userservice.service.dto.AuthUserDTO;
import org.khasanof.userservice.service.mapper.AuthUserMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.userservice.service.impl
 * @since 10/22/2023 9:25 PM
 */
@Slf4j
@Service
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserMapper mapper;
    private final AuthUserRepository repository;

    public AuthUserServiceImpl(AuthUserMapper mapper, AuthUserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Mono<Object> getToken(Mono<AuthRequestDTO> dto) {
        return null;
    }

    @Override
    public Mono<Void> create(Mono<AuthUserDTO> mono) {
        return mono.map(dto -> {
            AuthUser user = mapper.toEntity(dto);
            user.setStatus(Status.NEW);
            return user;
        }).flatMap(repository::save).then();
    }

    @Override
    public Mono<Void> update() {
        return null;
    }

}
