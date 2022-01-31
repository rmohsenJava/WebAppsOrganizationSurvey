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
public class WebsiteMaintenanceFees {

    @NotNull(message = "Total Amount can not be null!")
    Float totalPaymentAmount;

    @NotNull(message = "Payment Year can not be null !")
    String paymentYear;

}
