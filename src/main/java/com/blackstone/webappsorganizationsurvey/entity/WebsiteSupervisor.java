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
public class WebsiteSupervisor {

    @NotNull(message = "Number Of Supervisors can not be null!")
    Integer numberOfSupervisors;

    @NotNull(message = "Supervisor Position can not be null !")
    String supervisorPosition;

}
