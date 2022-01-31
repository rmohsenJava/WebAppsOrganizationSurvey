package com.blackstone.webappsorganizationsurvey.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ViolationErrors {

    private final String fieldName;
    private final String message;
}
