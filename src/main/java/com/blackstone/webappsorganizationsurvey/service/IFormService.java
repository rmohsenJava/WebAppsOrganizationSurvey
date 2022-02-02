package com.blackstone.webappsorganizationsurvey.service;

import com.blackstone.webappsorganizationsurvey.dto.FormRequest;
import com.blackstone.webappsorganizationsurvey.dto.FormResponse;
import com.blackstone.webappsorganizationsurvey.entity.Form;
import com.blackstone.webappsorganizationsurvey.exception.ContractFilesNotUploadedException;
import com.blackstone.webappsorganizationsurvey.exception.FormAlreadyCanceledException;
import com.blackstone.webappsorganizationsurvey.exception.FormAlreadyCompletedException;
import com.blackstone.webappsorganizationsurvey.exception.FormNotFoundException;
import org.springframework.data.domain.Page;

public interface IFormService {

    FormResponse submitForm(FormRequest formRequest) throws FormNotFoundException, FormAlreadyCompletedException, FormAlreadyCanceledException, ContractFilesNotUploadedException;

    Page<FormResponse> getAllForms(int offset, int pageSize);

    FormResponse getFormById(String id) throws FormNotFoundException;

    Form getFormById(Long id) throws FormNotFoundException;

    Form getFormByUUID(String uuid) throws FormNotFoundException;

    FormResponse initializeForm();
}
