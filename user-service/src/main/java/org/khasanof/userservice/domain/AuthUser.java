package org.khasanof.userservice.domain;

import lombok.*;
import org.khasanof.userservice.domain.enumeration.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.userservice.domain
 * @since 10/22/2023 7:25 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table("auth_user")
public class AuthUser extends AbstractAuditingEntity {

    @Id
    private Long id;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("username")
    private String username;

    @Column("phone_number")
    private String phoneNumber;

    @Column("email")
    private String email;

    @Column("password")
    private String password;

    @Column("status")
    private Status status;

    @Column("verification_code")
    private String verificationCode;

    @Column("is_active")
    private boolean isActive;

    @Column("is_reported")
    private boolean isReported;

    @Column("is_blocked")
    private boolean isBlocked;

    @Column("blocked_reason")
    private String blockedReason;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthUser authUser = (AuthUser) o;

        if (isActive != authUser.isActive) return false;
        if (isReported != authUser.isReported) return false;
        if (isBlocked != authUser.isBlocked) return false;
        if (!Objects.equals(id, authUser.id)) return false;
        if (!Objects.equals(firstName, authUser.firstName)) return false;
        if (!Objects.equals(lastName, authUser.lastName)) return false;
        if (!Objects.equals(username, authUser.username)) return false;
        if (!Objects.equals(phoneNumber, authUser.phoneNumber))
            return false;
        if (!Objects.equals(email, authUser.email)) return false;
        if (!Objects.equals(password, authUser.password)) return false;
        if (status != authUser.status) return false;
        if (!Objects.equals(verificationCode, authUser.verificationCode))
            return false;
        return Objects.equals(blockedReason, authUser.blockedReason);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (verificationCode != null ? verificationCode.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (isReported ? 1 : 0);
        result = 31 * result + (isBlocked ? 1 : 0);
        result = 31 * result + (blockedReason != null ? blockedReason.hashCode() : 0);
        return result;
    }
}
