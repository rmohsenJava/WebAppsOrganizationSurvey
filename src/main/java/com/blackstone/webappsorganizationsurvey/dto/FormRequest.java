package com.blackstone.webappsorganizationsurvey.dto;

import com.blackstone.webappsorganizationsurvey.entity.Company;
import com.blackstone.webappsorganizationsurvey.entity.CompanyDevelopmentFees;
import com.blackstone.webappsorganizationsurvey.entity.WebsiteMaintenanceFees;
import com.blackstone.webappsorganizationsurvey.entity.WebsiteSupervisor;
import com.blackstone.webappsorganizationsurvey.entity.enums.ServiceFollowUp;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class FormRequest {

    @NotNull(message = "Organization name can not be null!")
    private String organizationName;

    @NotNull(message = "Website Url can not be null!")
    @URL(message = "Website URL Must be a valid URL!")
    private String webSiteURL;

    @Valid
    private List<Company> companies;

    @Valid
    private List<CompanyDevelopmentFees> companyDevelopmentFees;

    @Valid
    private List<WebsiteMaintenanceFees> websiteMaintenanceFees;

    @Valid
    private List<WebsiteSupervisor> websiteSupervisors;

    @NotNull(message = "Website Software Company can not be null!")
    private String websiteSoftwareCompany;

    @NotNull(message = "Website Host Details can not be null!")
    private String websiteHostDetails;

    @NotNull(message = "Question must be answered !")
    private Boolean sourceCodeObtained;

    @NotNull(message = "Website Programming Language can not be null!")
    private String websiteProgrammingLanguage;

    private Boolean hasWebsiteExtraFees;

    private String websiteExtraFeesDetails;

    @NotNull(message = "Domain name fees can not be null !")
    private Float websiteDomainNameFees;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Follow up can not be null !")
    private ServiceFollowUp serviceFollowUp;

    private String serviceFollowUpDetails;

    @NotNull(message = "Question must be answered !")
    private Boolean hasComplainFeature;

    private String complainFeatureDetails;

    @NotNull(message = "Question must be answered !")
    private Boolean hasCustomerSatisfactionFeature;

    private String customerSatisfactionFeatureDetails;

    @NotNull(message = "Question must be answered !")
    private Boolean developedUsingLatestStandard;

    private String latestStandardDetails;

    @NotNull(message = "Question must be answered !")
    private Boolean preferStandardDevelopmentInstruction;

    @NotNull(message = "Question must be answered !")
    private Boolean suggestionsToShare;

    private String suggestionsDetails;

    @NotNull(message = "Question must be answered !")
    private Boolean continuousUpdate;

    @NotNull(message = "Question must be answered !")
    private Boolean hasSocialMediaAccounts;

    private String socialMediaAccountsDetails;

    private String customerAwarenessDetails;

    private String trackingFeatureDetails;

    private String continuousUpdateDetails;

    @NotNull(message = "Question must be answered !")
    private Boolean hasCustomerAwareness;

    @NotNull(message = "Question must be answered !")
    private Boolean securityProtocolsApplied;

    @NotNull(message = "How Maintenance Applied can not be null !")
    private String howMaintenanceApplied;

    @NotNull(message = "Question must be answered !")
    private Boolean hasTrackingFeature;

}
