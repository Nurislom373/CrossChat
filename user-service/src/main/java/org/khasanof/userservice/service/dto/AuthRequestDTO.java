package org.khasanof.userservice.service.dto;

import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

/**
 * @author Nurislom
 * @see org.khasanof.userservice.service.dto
 * @since 10/22/2023 8:42 PM
 */
@Getter
@Setter
@ToString
@ParameterObject
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDTO {

    private String username;
    private String password;
    private String rememberMe;

}
