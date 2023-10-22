package org.khasanof.userservice.service.mapper;

import org.khasanof.EntityMapper;
import org.khasanof.userservice.domain.AuthUser;
import org.khasanof.userservice.service.dto.AuthUserDTO;
import org.mapstruct.Mapper;

/**
 * @author Nurislom
 * @see org.khasanof.userservice.service.mapper
 * @since 10/22/2023 9:26 PM
 */
@Mapper(componentModel = "spring")
public interface AuthUserMapper extends EntityMapper<AuthUserDTO, AuthUser> {
}
