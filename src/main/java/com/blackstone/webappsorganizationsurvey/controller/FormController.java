package com.blackstone.webappsorganizationsurvey.controller;


import com.blackstone.webappsorganizationsurvey.dto.FormRequest;
import com.blackstone.webappsorganizationsurvey.dto.FormResponse;
import com.blackstone.webappsorganizationsurvey.entity.Form;
import com.blackstone.webappsorganizationsurvey.exception.FormNotFoundException;
import com.blackstone.webappsorganizationsurvey.service.IFormService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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


    @ApiOperation(value = "Get AL survey forms",
            notes = "Get AL survey forms"
    )
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<FormResponse> getForms() {
        return this.formService.getForms();
    }

    @ApiOperation(value = "Get survey forms BY ID",
            notes = "Get survey forms BY ID"
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FormResponse getFormById(@PathVariable String id) throws FormNotFoundException {
        return this.formService.getFormById(id);
    }
}
