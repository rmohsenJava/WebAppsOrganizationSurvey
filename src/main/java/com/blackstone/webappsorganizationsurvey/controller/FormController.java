package com.blackstone.webappsorganizationsurvey.controller;


import com.blackstone.webappsorganizationsurvey.dto.FileResponse;
import com.blackstone.webappsorganizationsurvey.dto.FormRequest;
import com.blackstone.webappsorganizationsurvey.dto.FormResponse;
import com.blackstone.webappsorganizationsurvey.entity.enums.FileType;
import com.blackstone.webappsorganizationsurvey.entity.enums.FormStatus;
import com.blackstone.webappsorganizationsurvey.exception.ContractFilesNotUploadedException;
import com.blackstone.webappsorganizationsurvey.exception.FormAlreadyCanceledException;
import com.blackstone.webappsorganizationsurvey.exception.FormAlreadyCompletedException;
import com.blackstone.webappsorganizationsurvey.exception.FormNotFoundException;
import com.blackstone.webappsorganizationsurvey.service.IFileService;
import com.blackstone.webappsorganizationsurvey.service.IFormService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/survey/form")
@Validated
public class FormController {

    private final IFormService formService;
    private final IFileService fileService;

    @ApiOperation(value = "Initialize new Form",
            notes = "Initialize new Form",
            response = FormResponse.class
    )
    @GetMapping("/initialize")
    @ResponseStatus(HttpStatus.OK)
    public FormResponse initializeForm() {
        return this.formService.initializeForm();
    }

    @ApiOperation(value = "Get survey forms by ID",
            notes = "Get survey forms by ID",
            response = FormResponse.class

    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FormResponse getFormById(@PathVariable String id) throws FormNotFoundException {
        return this.formService.getFormById(id);
    }

    @ApiOperation(value = "Get AL survey forms",
            notes = "Get AL survey forms",
            response = FormResponse.class
    )
    @GetMapping("/{offset}/{pageSize}")
    @ResponseStatus(HttpStatus.OK)
    public Page<FormResponse> getForms(@PathVariable int offset, @PathVariable int pageSize, @RequestParam FormStatus formStatus) {
        return this.formService.getAllForms(offset, pageSize, formStatus);
    }

    @ApiOperation(value = "Submit survey form",
            notes = "Submit Survey form",
            response = FileResponse.class
    )
    @PostMapping(value = "/file/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public List<FileResponse> uploadFile(@RequestPart("file") List<MultipartFile> files,
                                         @RequestParam("type") FileType fileType,
                                         @RequestParam("formId") Long formId) throws Exception {
        return this.fileService.upload(files, fileType, formId);
    }

    @ApiOperation(value = "Submit survey form",
            notes = "Submit Survey form",
            response = FormResponse.class
    )
    @PostMapping(value = "/submit")
    @ResponseStatus(HttpStatus.CREATED)
    public FormResponse submitForm(@RequestBody @Valid FormRequest formRequest) throws FormNotFoundException,
            FormAlreadyCompletedException, FormAlreadyCanceledException, ContractFilesNotUploadedException {

        return this.formService.submitForm(formRequest);
    }


}
