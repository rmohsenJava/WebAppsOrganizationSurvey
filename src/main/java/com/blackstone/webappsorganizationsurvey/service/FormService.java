package com.blackstone.webappsorganizationsurvey.service;

import com.blackstone.webappsorganizationsurvey.dto.FileResponse;
import com.blackstone.webappsorganizationsurvey.dto.FormRequest;
import com.blackstone.webappsorganizationsurvey.dto.FormResponse;
import com.blackstone.webappsorganizationsurvey.entity.Form;
import com.blackstone.webappsorganizationsurvey.entity.FormFile;
import com.blackstone.webappsorganizationsurvey.exception.FormNotFoundException;
import com.blackstone.webappsorganizationsurvey.repository.FormFileRepository;
import com.blackstone.webappsorganizationsurvey.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<FormResponse> getForms() {

        List<Form> formList = this.formRepository.findAll();

        return formList.stream()
                .map(form -> mapToDTO(form, mapToFileDTO(form.getFormFiles())))
                .collect(Collectors.toList());
    }

    @Override
    public FormResponse getFormById(String id) throws FormNotFoundException {

        return this.formRepository.findById(Long.parseLong(id))
                .map(formMap -> this.mapToDTO(formMap, this.mapToFileDTO(formMap.getFormFiles())))
                .orElseThrow(() -> new FormNotFoundException("Form Not Found"));
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

    private FormResponse mapToDTO(Form form, List<FileResponse> fileResponses) {

        return FormResponse.builder()
                .id(form.getId())
                .companies(form.getCompanies())
                .customerSatisfactionFeatureDetails(form.getCustomerSatisfactionFeatureDetails())
                .companyDevelopmentFees(form.getCompanyDevelopmentFees())
                .websiteMaintenanceFees(form.getWebsiteMaintenanceFees())
                .websiteSupervisors(form.getWebsiteSupervisors())
                .hasCustomerSatisfactionFeature(form.getHasCustomerSatisfactionFeature())
                .continuousUpdate(form.getContinuousUpdate())
                .developedUsingLatestStandard(form.getDevelopedUsingLatestStandard())
                .hasComplainFeature(form.getHasComplainFeature())
                .hasCustomerAwareness(form.getHasCustomerAwareness())
                .hasSocialMediaAccounts(form.getHasSocialMediaAccounts())
                .hasTrackingFeature(form.getHasTrackingFeature())
                .hasWebsiteExtraFees(form.getHasWebsiteExtraFees())
                .websiteDomainNameFees(form.getWebsiteDomainNameFees())
                .howMaintenanceApplied(form.getHowMaintenanceApplied())
                .latestStandardDetails(form.getLatestStandardDetails())
                .organizationName(form.getOrganizationName())
                .serviceFollowUp(form.getServiceFollowUp())
                .preferStandardDevelopmentInstruction(form.getPreferStandardDevelopmentInstruction())
                .securityProtocolsApplied(form.getSecurityProtocolsApplied())
                .sourceCodeObtained(form.getSourceCodeObtained())
                .suggestionsDetails(form.getSuggestionsDetails())
                .suggestionsToShare(form.getSuggestionsToShare())
                .websiteExtraFeesDetails(form.getWebsiteExtraFeesDetails())
                .websiteHostDetails(form.getWebsiteHostDetails())
                .websiteProgrammingLanguage(form.getWebsiteProgrammingLanguage())
                .websiteSoftwareCompany(form.getWebsiteSoftwareCompany())
                .webSiteURL(form.getWebSiteURL())
                .serviceFollowUpDetails(form.getServiceFollowUpDetails())
                .complainFeatureDetails(form.getComplainFeatureDetails())
                .socialMediaAccountsDetails(form.getSocialMediaAccountsDetails())
                .customerAwarenessDetails(form.getCustomerAwarenessDetails())
                .trackingFeatureDetails(form.getTrackingFeatureDetails())
                .continuousUpdateDetails(form.getContinuousUpdateDetails())
                .formFiles(fileResponses)
                .date(form.getSubmissionDate())
                .build();
    }

    private List<FileResponse> mapToFileDTO(List<FormFile> formFiles) {

        return formFiles.stream().map(formFile -> FileResponse.builder()
                        .id(formFile.getId())
                        .fileType(formFile.getFileType())
                        .type(formFile.getType())
                        .name(formFile.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
