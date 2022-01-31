package com.blackstone.webappsorganizationsurvey.service;

import com.blackstone.webappsorganizationsurvey.entity.Form;
import com.blackstone.webappsorganizationsurvey.entity.FormFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {


    List<FormFile> mapToEntity(MultipartFile[] contractFiles,
                               MultipartFile[] systemImages, MultipartFile[] securityProtocolsDocuments, Form form) throws Exception;

}
