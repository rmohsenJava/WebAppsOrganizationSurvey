package com.blackstone.webappsorganizationsurvey.entity;


import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Value
public class Company {

    @NotNull(message = "Company name can not be null !")
    String companyName;

    @NotNull(message = "Contract year can not be null !")
    String contractYear;

    @NotNull(message = "Reason can not be null !")
    String reasonForLeaving;

}
