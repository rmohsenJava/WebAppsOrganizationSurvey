package com.blackstone.webappsorganizationsurvey.service;

import com.blackstone.webappsorganizationsurvey.dto.FormRequest;
import com.blackstone.webappsorganizationsurvey.dto.FormResponse;
import com.blackstone.webappsorganizationsurvey.entity.Form;
import org.springframework.web.multipart.MultipartFile;

public interface IFormService {

    Form submitForm(FormRequest formRequest,
                    MultipartFile[] contractFiles,
                    MultipartFile[] systemImages,
                    MultipartFile[] securityProtocolsDocuments) throws Exception;
}
