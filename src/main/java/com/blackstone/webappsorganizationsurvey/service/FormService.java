package com.blackstone.webappsorganizationsurvey.service;

import com.blackstone.webappsorganizationsurvey.dto.FormRequest;
import com.blackstone.webappsorganizationsurvey.entity.Form;
import com.blackstone.webappsorganizationsurvey.entity.FormFile;
import com.blackstone.webappsorganizationsurvey.repository.FormFileRepository;
import com.blackstone.webappsorganizationsurvey.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class FormService implements IFormService {

    private final FormRepository formRepository;
    private final IFileService fileService;
    private final FormFileRepository formFileRepository;


    @Override
    @Transactional
    public Form submitForm(FormRequest formRequest,
                           MultipartFile[] contractFiles,
                           MultipartFile[] systemImages, MultipartFile[] securityProtocolsDocuments) throws Exception {

        Form savedForm = this.formRepository.save(this.mapToEntity(formRequest));

        List<FormFile> formFiles = this.fileService.mapToEntity(contractFiles, systemImages, securityProtocolsDocuments,
                savedForm);

        formFileRepository.saveAll(formFiles);

        return savedForm;
    }


    private Form mapToEntity(FormRequest formRequest) {

        return Form.builder()
                .companies(formRequest.getCompanies())
                .customerSatisfactionFeatureDetails(formRequest.getCustomerSatisfactionFeatureDetails())
                .companyDevelopmentFees(formRequest.getCompanyDevelopmentFees())
                .websiteMaintenanceFees(formRequest.getWebsiteMaintenanceFees())
                .websiteSupervisors(formRequest.getWebsiteSupervisors())
                .hasCustomerSatisfactionFeature(formRequest.getHasCustomerSatisfactionFeature())
                .continuousUpdate(formRequest.getContinuousUpdate())
                .developedUsingLatestStandard(formRequest.getDevelopedUsingLatestStandard())
                .hasComplainFeature(formRequest.getHasComplainFeature())
                .hasCustomerAwareness(formRequest.getHasCustomerAwareness())
                .hasSocialMediaAccounts(formRequest.getHasSocialMediaAccounts())
                .hasTrackingFeature(formRequest.getHasTrackingFeature())
                .hasWebsiteExtraFees(formRequest.getHasWebsiteExtraFees())
                .websiteDomainNameFees(formRequest.getWebsiteDomainNameFees())
                .howMaintenanceApplied(formRequest.getHowMaintenanceApplied())
                .latestStandardDetails(formRequest.getLatestStandardDetails())
                .organizationName(formRequest.getOrganizationName())
                .serviceFollowUp(formRequest.getServiceFollowUp())
                .preferStandardDevelopmentInstruction(formRequest.getPreferStandardDevelopmentInstruction())
                .securityProtocolsApplied(formRequest.getSecurityProtocolsApplied())
                .sourceCodeObtained(formRequest.getSourceCodeObtained())
                .suggestionsDetails(formRequest.getSuggestionsDetails())
                .suggestionsToShare(formRequest.getSuggestionsToShare())
                .websiteExtraFeesDetails(formRequest.getWebsiteExtraFeesDetails())
                .websiteHostDetails(formRequest.getWebsiteHostDetails())
                .websiteProgrammingLanguage(formRequest.getWebsiteProgrammingLanguage())
                .websiteSoftwareCompany(formRequest.getWebsiteSoftwareCompany())
                .webSiteURL(formRequest.getWebSiteURL())
                .serviceFollowUpDetails(formRequest.getServiceFollowUpDetails())
                .complainFeatureDetails(formRequest.getComplainFeatureDetails())
                .socialMediaAccountsDetails(formRequest.getSocialMediaAccountsDetails())
                .customerAwarenessDetails(formRequest.getCustomerAwarenessDetails())
                .trackingFeatureDetails(formRequest.getTrackingFeatureDetails())
                .continuousUpdateDetails(formRequest.getContinuousUpdateDetails())
                .build();
    }

}
