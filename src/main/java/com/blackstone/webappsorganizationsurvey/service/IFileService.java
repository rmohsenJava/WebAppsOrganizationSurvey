package com.blackstone.webappsorganizationsurvey.service;

import com.blackstone.webappsorganizationsurvey.dto.FileResponse;
import com.blackstone.webappsorganizationsurvey.entity.enums.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {

    /**
     * Upload files to the form
     *
     * @param files    to be uploaded
     * @param fileType file type @{@link FileType}
     * @param formId   form ID
     * @return @{@link List} of uploaded files of type @{@link FileResponse}
     * @throws Exception if validation fails
     */
    List<FileResponse> upload(List<MultipartFile> files, FileType fileType, Long formId) throws Exception;

}
