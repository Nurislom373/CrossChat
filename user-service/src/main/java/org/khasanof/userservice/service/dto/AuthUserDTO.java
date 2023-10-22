package org.khasanof.userservice.service.dto;

import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

/**
 * @author Nurislom
 * @see org.khasanof.userservice.service.dto
 * @since 10/22/2023 8:44 PM
 */
@Getter
@Setter
@ToString
@ParameterObject
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String email;
    private String password;

}
