package com.blackstone.webappsorganizationsurvey.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AuthenticationRequest {


    @NotBlank
    private final String username;

    @NotBlank
    private final String password;
}
