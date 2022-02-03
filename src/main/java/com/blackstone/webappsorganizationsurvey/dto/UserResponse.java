package com.blackstone.webappsorganizationsurvey.dto;


import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponse {

    private String email;

    private String username;

    private LocalDateTime registeredAt;
}
