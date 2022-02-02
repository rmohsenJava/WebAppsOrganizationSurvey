package com.blackstone.webappsorganizationsurvey.service;

import com.blackstone.webappsorganizationsurvey.dto.FormRequest;
import com.blackstone.webappsorganizationsurvey.dto.FormResponse;
import com.blackstone.webappsorganizationsurvey.entity.Form;
import com.blackstone.webappsorganizationsurvey.exception.FormNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface IFormService {

    Form submitForm(FormRequest formRequest,
                    MultipartFile[] contractFiles,
                    MultipartFile[] systemImages,
                    MultipartFile[] securityProtocolsDocuments) throws Exception;

    Page<FormResponse> getForms(int offset, int pageSize);

    FormResponse getFormById(String id) throws FormNotFoundException;
}
