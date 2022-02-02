package com.blackstone.webappsorganizationsurvey.service;


import com.blackstone.webappsorganizationsurvey.dto.FileResponse;
import com.blackstone.webappsorganizationsurvey.entity.Form;
import com.blackstone.webappsorganizationsurvey.entity.FormFile;
import com.blackstone.webappsorganizationsurvey.entity.enums.FileType;
import com.blackstone.webappsorganizationsurvey.entity.enums.FormStatus;
import com.blackstone.webappsorganizationsurvey.exception.FormAlreadyCanceledException;
import com.blackstone.webappsorganizationsurvey.exception.FormAlreadyCompletedException;
import com.blackstone.webappsorganizationsurvey.repository.FormFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class FileService implements IFileService {

    private final IFormService formService;
    private final FormFileRepository formFileRepository;

    @Override
    public List<FileResponse> upload(List<MultipartFile> files, FileType fileType, Long formId) throws Exception {


        Long formFilesCount = this.formFileRepository.countAllByForm_IdAndFileType(formId, fileType);

        Form form = this.formService.getFormById(formId);

        if (formFilesCount + files.size() > 7) {
            throw new Exception("Maximum number of " + fileType.name() + " files is reached !");
        }

        if (FormStatus.COMPLETED.name().equals(form.getFormStatus().name())) {
            throw new FormAlreadyCompletedException("Form with uuid [" + form.getUuid() + "] already completed !");
        }

        if (FormStatus.CANCELED.name().equals(form.getFormStatus().name())) {
            throw new FormAlreadyCanceledException("Form with uuid [" + form.getUuid() + "] already canceled !");
        }

        return mapToFileDTO(this.formFileRepository.saveAll(files.stream().map(multipartFile -> {
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
        }).collect(Collectors.toList())));

    }

    public static List<FileResponse> mapToFileDTO(List<FormFile> formFiles) {

        return formFiles.stream().map(formFile -> FileResponse.builder()
                        .id(formFile.getId())
                        .fileType(formFile.getFileType())
                        .type(formFile.getType())
                        .name(formFile.getName())
                        .build())
                .collect(Collectors.toList());
    }

}
