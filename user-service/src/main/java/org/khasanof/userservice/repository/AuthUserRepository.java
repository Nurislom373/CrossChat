package org.khasanof.userservice.repository;

import org.khasanof.userservice.domain.AuthUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nurislom
 * @see org.khasanof.userservice.repository
 * @since 10/22/2023 9:31 PM
 */
@Repository
public interface AuthUserRepository extends ReactiveCrudRepository<AuthUser, Long> {
}
