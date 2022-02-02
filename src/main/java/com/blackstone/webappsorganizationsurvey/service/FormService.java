package com.blackstone.webappsorganizationsurvey.service;

import com.blackstone.webappsorganizationsurvey.dto.FileResponse;
import com.blackstone.webappsorganizationsurvey.dto.FormRequest;
import com.blackstone.webappsorganizationsurvey.dto.FormResponse;
import com.blackstone.webappsorganizationsurvey.entity.Form;
import com.blackstone.webappsorganizationsurvey.entity.enums.FileType;
import com.blackstone.webappsorganizationsurvey.entity.enums.FormStatus;
import com.blackstone.webappsorganizationsurvey.entity.enums.ServiceFollowUp;
import com.blackstone.webappsorganizationsurvey.exception.ContractFilesNotUploadedException;
import com.blackstone.webappsorganizationsurvey.exception.FormAlreadyCanceledException;
import com.blackstone.webappsorganizationsurvey.exception.FormAlreadyCompletedException;
import com.blackstone.webappsorganizationsurvey.exception.FormNotFoundException;
import com.blackstone.webappsorganizationsurvey.repository.FormRepository;
import com.blackstone.webappsorganizationsurvey.util.FormActionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class FormService implements IFormService {

    private final FormRepository formRepository;

    /**
     * #{@inheritDoc}
     */
    @Override
    @Transactional
    public FormResponse submitForm(FormRequest formRequest) throws FormNotFoundException,
            FormAlreadyCompletedException, FormAlreadyCanceledException, ContractFilesNotUploadedException {


        log.info("Start submitting form : {}", formRequest);

        Form savedForm = this.getFormByUUID(formRequest.getUuid());

        FormActionValidator.checkActionValidity(savedForm, formRequest.getUuid());

        if (savedForm.getFormFiles()
                .stream()
                .noneMatch(type -> type.getFileType()
                        .name().equals(FileType.SYSTEM_CONTRACT_FILES.name()))) {
            throw new ContractFilesNotUploadedException("System Contract Files are Required, Please upload required files first !");
        }

        this.mapToFormEntity(savedForm, formRequest);

        Form updatedForm = this.formRepository.save(savedForm);

        return this.mapToFormDTO(updatedForm, FileService.mapToFileDTO(updatedForm.getFormFiles()));
    }

    /**
     * #{@inheritDoc}
     */
    @Override
    public FormResponse initializeForm() {

        Form form = Form.builder().companies(Collections.emptyList())
                .customerSatisfactionFeatureDetails("")
                .companyDevelopmentFees(Collections.emptyList())
                .websiteMaintenanceFees(Collections.emptyList())
                .websiteSupervisors(Collections.emptyList())
                .uuid(UUID.randomUUID().toString())
                .formStatus(FormStatus.IN_PROGRESS)
                .hasCustomerSatisfactionFeature(false)
                .continuousUpdate(false)
                .developedUsingLatestStandard(false)
                .hasComplainFeature(false)
                .hasCustomerAwareness(false)
                .hasSocialMediaAccounts(false)
                .hasTrackingFeature(false)
                .hasWebsiteExtraFees(false)
                .websiteDomainNameFees((float) 0)
                .howMaintenanceApplied("")
                .latestStandardDetails("")
                .organizationName("Blackstone EIT")
                .serviceFollowUp(ServiceFollowUp.AUTOMATIC)
                .preferStandardDevelopmentInstruction(false)
                .securityProtocolsApplied(false)
                .sourceCodeObtained(false)
                .suggestionsDetails("")
                .suggestionsToShare(false)
                .websiteExtraFeesDetails("")
                .websiteHostDetails("")
                .websiteProgrammingLanguage("")
                .websiteSoftwareCompany("")
                .webSiteURL("https://xyz.com")
                .serviceFollowUpDetails("")
                .complainFeatureDetails("")
                .socialMediaAccountsDetails("")
                .customerAwarenessDetails("")
                .trackingFeatureDetails("")
                .continuousUpdateDetails("")
                .build();

        return this.mapToFormDTO(this.formRepository.save(form), Collections.emptyList());
    }

    /**
     * #{@inheritDoc}
     */
    @Override
    public Page<FormResponse> getAllForms(int offset, int pageSize) {

        PageRequest pageRequest = PageRequest.of(offset, pageSize, Sort.by("submissionDate").descending());

        Page<Form> formList = this.formRepository.findAll(pageRequest);

        return new PageImpl<>(formList.getContent().stream()
                .map(form -> mapToFormDTO(form, FileService.mapToFileDTO(form.getFormFiles())))
                .collect(Collectors.toList()), pageRequest, formList.getTotalElements());

    }

    /**
     * #{@inheritDoc}
     */
    @Override
    public FormResponse getFormById(String id) throws FormNotFoundException {

        return this.formRepository.findById(Long.parseLong(id))
                .map(formMap -> this.mapToFormDTO(formMap, FileService.mapToFileDTO(formMap.getFormFiles())))
                .orElseThrow(() -> new FormNotFoundException("Form Not Found"));
    }

    /**
     * #{@inheritDoc}
     */
    @Override
    public Form getFormById(Long id) throws FormNotFoundException {
        return this.formRepository.findById(id)
                .orElseThrow(() -> new FormNotFoundException("Form Not Found"));
    }

    /**
     * #{@inheritDoc}
     */
    @Override
    public Form getFormByUUID(String uuid) throws FormNotFoundException {
        return this.formRepository.findByUuid(uuid).orElseThrow(() -> new FormNotFoundException("Form w Not Found"));
    }

    private void mapToFormEntity(Form savedForm, FormRequest formRequest) {

        savedForm.setCompanies(formRequest.getCompanies());
        savedForm.setFormStatus(FormStatus.COMPLETED);
        savedForm.setCustomerSatisfactionFeatureDetails(formRequest.getCustomerSatisfactionFeatureDetails());
        savedForm.setCompanyDevelopmentFees(formRequest.getCompanyDevelopmentFees());
        savedForm.setWebsiteMaintenanceFees(formRequest.getWebsiteMaintenanceFees());
        savedForm.setWebsiteSupervisors(formRequest.getWebsiteSupervisors());
        savedForm.setHasCustomerSatisfactionFeature(formRequest.getHasCustomerSatisfactionFeature());
        savedForm.setContinuousUpdate(formRequest.getContinuousUpdate());
        savedForm.setDevelopedUsingLatestStandard(formRequest.getDevelopedUsingLatestStandard());
        savedForm.setHasComplainFeature(formRequest.getHasComplainFeature());
        savedForm.setHasCustomerAwareness(formRequest.getHasCustomerAwareness());
        savedForm.setHasSocialMediaAccounts(formRequest.getHasSocialMediaAccounts());
        savedForm.setHasTrackingFeature(formRequest.getHasTrackingFeature());
        savedForm.setHasWebsiteExtraFees(formRequest.getHasWebsiteExtraFees());
        savedForm.setWebsiteDomainNameFees(formRequest.getWebsiteDomainNameFees());
        savedForm.setHowMaintenanceApplied(formRequest.getHowMaintenanceApplied());
        savedForm.setLatestStandardDetails(formRequest.getLatestStandardDetails());
        savedForm.setOrganizationName(formRequest.getOrganizationName());
        savedForm.setServiceFollowUp(formRequest.getServiceFollowUp());
        savedForm.setPreferStandardDevelopmentInstruction(formRequest.getPreferStandardDevelopmentInstruction());
        savedForm.setSecurityProtocolsApplied(formRequest.getSecurityProtocolsApplied());
        savedForm.setSourceCodeObtained(formRequest.getSourceCodeObtained());
        savedForm.setSuggestionsDetails(formRequest.getSuggestionsDetails());
        savedForm.setSuggestionsToShare(formRequest.getSuggestionsToShare());
        savedForm.setWebsiteExtraFeesDetails(formRequest.getWebsiteExtraFeesDetails());
        savedForm.setWebsiteHostDetails(formRequest.getWebsiteHostDetails());
        savedForm.setWebsiteProgrammingLanguage(formRequest.getWebsiteProgrammingLanguage());
        savedForm.setWebsiteSoftwareCompany(formRequest.getWebsiteSoftwareCompany());
        savedForm.setWebSiteURL(formRequest.getWebSiteURL());
        savedForm.setServiceFollowUpDetails(formRequest.getServiceFollowUpDetails());
        savedForm.setComplainFeatureDetails(formRequest.getComplainFeatureDetails());
        savedForm.setSocialMediaAccountsDetails(formRequest.getSocialMediaAccountsDetails());
        savedForm.setCustomerAwarenessDetails(formRequest.getCustomerAwarenessDetails());
        savedForm.setTrackingFeatureDetails(formRequest.getTrackingFeatureDetails());
        savedForm.setContinuousUpdateDetails(formRequest.getContinuousUpdateDetails());

    }

    private FormResponse mapToFormDTO(Form form, List<FileResponse> fileResponses) {

        return FormResponse.builder()
                .id(form.getId())
                .companies(form.getCompanies())
                .customerSatisfactionFeatureDetails(form.getCustomerSatisfactionFeatureDetails())
                .companyDevelopmentFees(form.getCompanyDevelopmentFees())
                .websiteMaintenanceFees(form.getWebsiteMaintenanceFees())
                .uuid(form.getUuid())
                .formStatus(form.getFormStatus())
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

}
