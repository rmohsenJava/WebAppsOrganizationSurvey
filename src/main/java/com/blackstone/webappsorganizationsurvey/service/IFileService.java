package com.blackstone.webappsorganizationsurvey.service;

import com.blackstone.webappsorganizationsurvey.dto.FileResponse;
import com.blackstone.webappsorganizationsurvey.entity.enums.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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


    /**
     * Delete File
     *
     * @param id file id
     * @throws Exception if file not found
     */
    Map<String, Integer> removeFile(Integer id) throws Exception;
}
