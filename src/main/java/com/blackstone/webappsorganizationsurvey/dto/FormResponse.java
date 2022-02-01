package com.blackstone.webappsorganizationsurvey.dto;

import com.blackstone.webappsorganizationsurvey.entity.Company;
import com.blackstone.webappsorganizationsurvey.entity.CompanyDevelopmentFees;
import com.blackstone.webappsorganizationsurvey.entity.WebsiteMaintenanceFees;
import com.blackstone.webappsorganizationsurvey.entity.WebsiteSupervisor;
import com.blackstone.webappsorganizationsurvey.entity.enums.ServiceFollowUp;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class FormResponse {

    private Long id;

    private String organizationName;

    private String webSiteURL;

    private List<Company> companies;

    private List<CompanyDevelopmentFees> companyDevelopmentFees;

    private List<WebsiteMaintenanceFees> websiteMaintenanceFees;

    private List<WebsiteSupervisor> websiteSupervisors;

    private String websiteSoftwareCompany;

    private String websiteHostDetails;

    private Boolean sourceCodeObtained;

    private String websiteProgrammingLanguage;

    private Boolean hasWebsiteExtraFees;

    private String websiteExtraFeesDetails;

    private Float websiteDomainNameFees;

    private ServiceFollowUp serviceFollowUp;

    private String serviceFollowUpDetails;

    private Boolean hasComplainFeature;

    private String complainFeatureDetails;

    private Boolean hasCustomerSatisfactionFeature;

    private String customerSatisfactionFeatureDetails;

    private Boolean developedUsingLatestStandard;

    private String latestStandardDetails;

    private Boolean preferStandardDevelopmentInstruction;

    private Boolean suggestionsToShare;

    private String suggestionsDetails;

    private Boolean continuousUpdate;

    private Boolean hasSocialMediaAccounts;

    private String socialMediaAccountsDetails;

    private String customerAwarenessDetails;

    private String trackingFeatureDetails;

    private String continuousUpdateDetails;

    private Boolean hasCustomerAwareness;

    private Boolean securityProtocolsApplied;

    private String howMaintenanceApplied;

    private Boolean hasTrackingFeature;

    private LocalDateTime date;

    private List<FileResponse> formFiles;
}
