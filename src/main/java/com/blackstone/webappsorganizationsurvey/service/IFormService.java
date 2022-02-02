package com.blackstone.webappsorganizationsurvey.service;

import com.blackstone.webappsorganizationsurvey.dto.FormRequest;
import com.blackstone.webappsorganizationsurvey.dto.FormResponse;
import com.blackstone.webappsorganizationsurvey.entity.Form;
import com.blackstone.webappsorganizationsurvey.entity.enums.FormStatus;
import com.blackstone.webappsorganizationsurvey.exception.ContractFilesNotUploadedException;
import com.blackstone.webappsorganizationsurvey.exception.FormAlreadyCanceledException;
import com.blackstone.webappsorganizationsurvey.exception.FormAlreadyCompletedException;
import com.blackstone.webappsorganizationsurvey.exception.FormNotFoundException;
import org.springframework.data.domain.Page;

public interface IFormService {

    /**
     * Submit Form
     *
     * @param formRequest @{@link FormRequest}
     * @return Submitted Form
     * @throws FormNotFoundException             if form not found
     * @throws FormAlreadyCompletedException     form already completed
     * @throws FormAlreadyCanceledException      form already canceled
     * @throws ContractFilesNotUploadedException no contract files have been uploaded
     */
    FormResponse submitForm(FormRequest formRequest) throws FormNotFoundException, FormAlreadyCompletedException, FormAlreadyCanceledException, ContractFilesNotUploadedException;


    /**
     * Get list of all forms
     *
     * @param offset     @{@link Integer} start page
     * @param pageSize   @{@link Integer} page size
     * @param formStatus @{@link FormStatus} formStatus
     * @return paged list of @{@link FormResponse}
     */
    Page<FormResponse> getAllForms(int offset, int pageSize, FormStatus formStatus);

    /**
     * Get Form By I'd
     *
     * @param id form id
     * @return @{@link FormResponse}
     * @throws FormNotFoundException if form not found
     */
    FormResponse getFormById(String id) throws FormNotFoundException;

    /**
     * Get Form By I'd
     *
     * @param id form id
     * @return @{@link FormResponse}
     * @throws FormNotFoundException if form not found
     */
    Form getFormById(Long id) throws FormNotFoundException;

    /**
     * Get Form By I'd
     *
     * @param uuid form uuid
     * @return @{@link FormResponse}
     * @throws FormNotFoundException if form not found
     */
    Form getFormByUUID(String uuid) throws FormNotFoundException;

    /**
     * @return initialized @{@link FormResponse}
     */
    FormResponse initializeForm();
}
