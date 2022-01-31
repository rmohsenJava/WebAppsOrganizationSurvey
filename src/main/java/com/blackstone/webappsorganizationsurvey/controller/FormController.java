package com.blackstone.webappsorganizationsurvey.controller;


import com.blackstone.webappsorganizationsurvey.dto.FormRequest;
import com.blackstone.webappsorganizationsurvey.entity.Form;
import com.blackstone.webappsorganizationsurvey.service.IFormService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/survey/form")
@Validated
public class FormController {

    private final IFormService formService;

    @ApiOperation(value = "Submit survey form",
            notes = "Submit Survey form"
    )
    @PostMapping(value = "/submit", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Form submitForm(@RequestPart(value = "contractFiles") MultipartFile[] contractFiles,
                           @RequestPart(value = "systemImages", required = false) MultipartFile[] systemImages,
                           @RequestPart(value = "securityProtocolsDocuments", required = false)
                                   MultipartFile[] securityProtocolsDocuments,
                           @RequestParam("form") FormRequest formRequest) throws Exception {

        return this.formService
                .submitForm(formRequest, contractFiles, systemImages, securityProtocolsDocuments);
    }
}
