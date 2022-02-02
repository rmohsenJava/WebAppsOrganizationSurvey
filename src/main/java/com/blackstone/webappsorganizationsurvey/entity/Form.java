package com.blackstone.webappsorganizationsurvey.entity;

import com.blackstone.webappsorganizationsurvey.entity.enums.FormStatus;
import com.blackstone.webappsorganizationsurvey.entity.enums.ServiceFollowUp;
import com.blackstone.webappsorganizationsurvey.util.CompanyDevelopmentFeesJsonConverter;
import com.blackstone.webappsorganizationsurvey.util.CompanyJsonConverter;
import com.blackstone.webappsorganizationsurvey.util.WebsiteMaintenanceFeesJsonConverter;
import com.blackstone.webappsorganizationsurvey.util.WebsiteSupervisorJsonConverter;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "form")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "UUID Can not be null !")
    @Column(unique = true, updatable = false)
    private String uuid;

    @Column(nullable = false, name = "organization_name")
    @NotNull(message = "Organization name can not be null!")
    private String organizationName;

    @Column(nullable = false, name = "website_url")
    @NotNull(message = "Website Url can not be null!")
    @URL(message = "Website URL Must be a valid URL!")
    private String webSiteURL;

    @Column(nullable = false, name = "website_software_company")
    @NotNull(message = "Website Software Company can not be null!")
    private String websiteSoftwareCompany;

    @Column(nullable = false, name = "website_host_details")
    @NotNull(message = "Website Host Details can not be null!")
    private String websiteHostDetails;

    @Column(nullable = false, name = "source_code_obtained")
    @NotNull(message = "Question must be answered !")
    private Boolean sourceCodeObtained;

    @Convert(converter = CompanyJsonConverter.class)
    @Lob
    @Valid
    private List<Company> companies;

    @Convert(converter = CompanyDevelopmentFeesJsonConverter.class)
    @Lob
    @Valid
    private List<CompanyDevelopmentFees> companyDevelopmentFees;

    @Convert(converter = WebsiteMaintenanceFeesJsonConverter.class)
    @Lob
    @Valid
    private List<WebsiteMaintenanceFees> websiteMaintenanceFees;

    @Convert(converter = WebsiteSupervisorJsonConverter.class)
    @Lob
    @Valid
    private List<WebsiteSupervisor> websiteSupervisors;

    @Column(nullable = false, name = "website_programming_language")
    @NotNull(message = "Website Programming Language can not be null!")
    private String websiteProgrammingLanguage;

    @Column(name = "has_website_extra_fees")
    private Boolean hasWebsiteExtraFees;

    @Column(name = "website_extra_fees_details")
    private String websiteExtraFeesDetails;

    @Column(nullable = false, name = "website_domain_name_fees")
    @NotNull(message = "Domain name fees can not be null !")
    private Float websiteDomainNameFees;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "service_follow_up")
    @NotNull(message = "Follow up can not be null !")
    private ServiceFollowUp serviceFollowUp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "form_status")
    @NotNull(message = "Status  can not be null !")
    private FormStatus formStatus;

    @Column(name = "service_follow_up_details")
    private String serviceFollowUpDetails;

    @Column(nullable = false, name = "has_complain_feature")
    @NotNull(message = "Question must be answered !")
    private Boolean hasComplainFeature;

    @Column(name = "complain_feature_details")
    private String complainFeatureDetails;

    @Column(nullable = false, name = "has_customer_satisfaction_feature")
    @NotNull(message = "Question must be answered !")
    private Boolean hasCustomerSatisfactionFeature;

    @Column(name = "customer_satisfaction_feature_details")
    private String customerSatisfactionFeatureDetails;

    @Column(nullable = false, name = "developed_using_latest_standard")
    @NotNull(message = "Question must be answered !")
    private Boolean developedUsingLatestStandard;

    @Column(name = "latest_standard_details")
    private String latestStandardDetails;

    @Column(nullable = false, name = "prefer_standard_development_instruction")
    @NotNull(message = "Question must be answered !")
    private Boolean preferStandardDevelopmentInstruction;

    @Column(nullable = false, name = "suggestions_to_share")
    @NotNull(message = "Question must be answered !")
    private Boolean suggestionsToShare;

    @Column(name = "suggestions_details")
    private String suggestionsDetails;

    @Column(nullable = false, name = "continuous_update")
    @NotNull(message = "Question must be answered !")
    private Boolean continuousUpdate;

    @Column(name = "continuous_update_details")
    private String continuousUpdateDetails;

    @Column(nullable = false, name = "has_social_media_accounts")
    @NotNull(message = "Question must be answered !")
    private Boolean hasSocialMediaAccounts;

    @Column(name = "social_media_accounts_details")
    private String socialMediaAccountsDetails;

    @Column(nullable = false, name = "has_customer_awareness")
    @NotNull(message = "Question must be answered !")
    private Boolean hasCustomerAwareness;

    @Column(name = "customer_awareness_details")
    private String customerAwarenessDetails;

    @Column(nullable = false, name = "security_protocols_applied")
    @NotNull(message = "Question must be answered !")
    private Boolean securityProtocolsApplied;

    @Column(name = "how_maintenance_applied")
    @NotNull(message = "How Maintenance Applied can not be null !")
    private String howMaintenanceApplied;

    @Column(nullable = false, name = "has_tracking_feature")
    @NotNull(message = "Question must be answered !")
    private Boolean hasTrackingFeature;

    @Column(name = "tracking_feature_details")
    private String trackingFeatureDetails;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<FormFile> formFiles = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime submissionDate;
}
