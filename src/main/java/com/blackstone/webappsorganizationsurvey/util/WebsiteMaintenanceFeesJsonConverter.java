package com.blackstone.webappsorganizationsurvey.util;

import com.blackstone.webappsorganizationsurvey.entity.WebsiteMaintenanceFees;
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
public class WebsiteMaintenanceFeesJsonConverter implements
        AttributeConverter<List<WebsiteMaintenanceFees>, String> {

    @Override
    public String convertToDatabaseColumn(List<WebsiteMaintenanceFees> websiteMaintenanceFees) {
        String websiteMFeesJson = null;
        try {
            websiteMFeesJson = JsonParser.serialize(websiteMaintenanceFees);
        } catch (final JsonProcessingException e) {
            log.error("Website Maintenance Fees JSON writing error", e);
        }

        return websiteMFeesJson;
    }

    @Override
    public List<WebsiteMaintenanceFees> convertToEntityAttribute(String s) {
        try {

            final ObjectMapper objectMapper = new ObjectMapper();
            WebsiteMaintenanceFees[] websiteMaintenanceFees = objectMapper.readValue(s,
                    WebsiteMaintenanceFees[].class);
            return Arrays.asList(websiteMaintenanceFees);

        } catch (final IOException e) {
            log.error("Website Maintenance Fees JSON reading error", e);
            return Collections.emptyList();
        }
    }
}
