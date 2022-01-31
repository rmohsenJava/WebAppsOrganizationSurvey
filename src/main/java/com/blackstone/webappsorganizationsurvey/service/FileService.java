package com.blackstone.webappsorganizationsurvey.service;


import com.blackstone.webappsorganizationsurvey.entity.Form;
import com.blackstone.webappsorganizationsurvey.entity.FormFile;
import com.blackstone.webappsorganizationsurvey.entity.enums.FileType;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Log4j2
public class FileService implements IFileService {

    @Override
    public List<FormFile> mapToEntity(MultipartFile[] contractFiles,
                                      MultipartFile[] systemImages,
                                      MultipartFile[] securityProtocolsDocuments, Form form) {

        List<FormFile> mappedContractFiles = getFormFiles(contractFiles,
                FileType.SYSTEM_CONTRACT_FILES, form);

        if (systemImages != null) {
            mappedContractFiles.addAll(getFormFiles(systemImages,
                    FileType.SYSTEM_IMAGE_FILES, form));
        }

        if (systemImages != null) {
            mappedContractFiles.addAll(getFormFiles(securityProtocolsDocuments,
                    FileType.SECURITY_PROTOCOL_DOCUMENTS, form));
        }

        return mappedContractFiles;

    }


    private List<FormFile> getFormFiles(MultipartFile[] files, FileType fileType, Form form) {
        return Arrays.stream(files).map(multipartFile -> {
            try {
                return FormFile.builder()
                        .name(StringUtils.cleanPath(Objects
                                .requireNonNull(multipartFile
                                        .getOriginalFilename())))
                        .type(multipartFile.getContentType())
                        .fileType(fileType)
                        .data(multipartFile.getBytes())
                        .form(form).build();

            } catch (IOException e) {
                log.error("Error while getting file info " + e.getMessage());
            }
            return null;
        }).collect(Collectors.toList());
    }
}
