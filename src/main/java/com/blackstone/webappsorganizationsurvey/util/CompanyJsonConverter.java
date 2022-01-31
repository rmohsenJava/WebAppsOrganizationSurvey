package com.blackstone.webappsorganizationsurvey.util;

import com.blackstone.webappsorganizationsurvey.entity.Company;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Log4j2
@Converter
public class CompanyJsonConverter implements AttributeConverter<List<Company>, String> {


    @Override
    public String convertToDatabaseColumn(List<Company> company) {

        String companyJson = null;
        try {
            companyJson = JsonParser.serialize(company);
        } catch (final JsonProcessingException e) {
            log.error("Company  JSON writing error", e);
        }

        return companyJson;
    }

    @Override
    public List<Company> convertToEntityAttribute(String companyJson) {

        try {

            final ObjectMapper objectMapper = new ObjectMapper();
            Company[] companies = objectMapper.readValue(companyJson, Company[].class);
            return Arrays.asList(companies);

        } catch (final IOException e) {
            log.error("Company JSON reading error", e);
            return Collections.emptyList();
        }
    }
}
