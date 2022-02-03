package com.blackstone.webappsorganizationsurvey.dto;


import lombok.*;

@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AuthenticationResponse {

    private final String jwt;
}
