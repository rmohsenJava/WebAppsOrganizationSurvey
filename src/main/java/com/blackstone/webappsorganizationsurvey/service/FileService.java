package com.blackstone.webappsorganizationsurvey.service;


import com.blackstone.webappsorganizationsurvey.dto.FileResponse;
import com.blackstone.webappsorganizationsurvey.entity.Form;
import com.blackstone.webappsorganizationsurvey.entity.FormFile;
import com.blackstone.webappsorganizationsurvey.entity.enums.FileType;
import com.blackstone.webappsorganizationsurvey.repository.FormFileRepository;
import com.blackstone.webappsorganizationsurvey.util.FormActionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    /**
     * #{@inheritDoc}
     */
    @Override
    @Transactional
    public void removeFile(Integer id) throws Exception {

        FormFile formFile = this.formFileRepository.findById(id)
                .orElseThrow(() -> new Exception("File Not Found"));

        this.formFileRepository.delete(formFile);
    }

    /**
     * #{@inheritDoc}
     */
    @Override
    public List<FileResponse> upload(List<MultipartFile> files, FileType fileType, Long formId) throws Exception {

        log.info("Start uploading {} files to form with ID {}", fileType, formId);

        Long formFilesCount = this.formFileRepository.countAllByForm_IdAndFileType(formId, fileType);

        log.info("Found {} files of type {} already uploaded", formFilesCount, fileType);

        Form form = this.formService.getFormById(formId);

        if (formFilesCount + files.size() > 7) {

            log.info("Maximum number of {} file types is reached", fileType);

            throw new Exception("Maximum number of " + fileType.name() + " files is reached !");
        }

        FormActionValidator.checkActionValidity(form, form.getUuid());

        return mapToFileDTO(this.formFileRepository.saveAll(files.stream().map(multipartFile -> {
            try {
                return FormFile.builder()
                        .name(StringUtils.cleanPath(Objects
                                .requireNonNull(multipartFile
                                        .getOriginalFilename())))
                        .type(multipartFile.getContentType())
                        .fileType(fileType)
                        .fileSize(multipartFile.getSize())
                        .data(multipartFile.getBytes())
                        .form(form).build();

            } catch (IOException e) {
                log.error("Error while getting file info " + e.getMessage());
            }
            return null;
        }).collect(Collectors.toList())));

    }

    /**
     * map File Entity to @{@link FileResponse}
     *
     * @param formFiles list of @{@link FormFile}
     * @return @{@link List} of @{@link FileResponse}
     */
    public static List<FileResponse> mapToFileDTO(List<FormFile> formFiles) {

        return formFiles.stream().map(formFile -> FileResponse.builder()
                        .id(formFile.getId())
                        .fileType(formFile.getFileType())
                        .type(formFile.getType())
                        .name(formFile.getName())
                        .fileSize(formFile.getFileSize() / 1024 + " كيلوبايت ")
                        .build())
                .collect(Collectors.toList());
    }

}
