package com.blackstone.webappsorganizationsurvey.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class FormResponse {

    private Long id;

    private LocalDateTime submittedDate;
}
