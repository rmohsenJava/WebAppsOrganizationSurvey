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
public class CompanyDevelopmentFees {

    @NotNull(message = "Company name can not be null !")
    String companyName;

    @NotNull(message = "Year can not be null !")
    String year;

    @NotNull(message = "Amount can not be null!")
    Float paymentAmount;
}
