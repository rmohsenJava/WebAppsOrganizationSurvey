package com.blackstone.webappsorganizationsurvey.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class UserRequest {

    @Email
    @Column(unique = true, nullable = false)
    @NotNull
    private String email;

    @Column(unique = true, nullable = false)
    @NotNull
    private String username;

    @Column(nullable = false)
    @NotNull
    @Length(min = 8)
    private String password;

}
