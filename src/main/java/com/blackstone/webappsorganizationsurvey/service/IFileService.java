package com.blackstone.webappsorganizationsurvey.service;

import com.blackstone.webappsorganizationsurvey.dto.FileResponse;
import com.blackstone.webappsorganizationsurvey.entity.enums.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {

    List<FileResponse> upload(List<MultipartFile> files, FileType fileType, Long formId) throws Exception;

}
