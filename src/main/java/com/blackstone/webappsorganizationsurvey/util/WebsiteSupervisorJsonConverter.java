package com.blackstone.webappsorganizationsurvey.util;

import com.blackstone.webappsorganizationsurvey.entity.WebsiteSupervisor;
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
public class WebsiteSupervisorJsonConverter implements
        AttributeConverter<List<WebsiteSupervisor>, String> {

    @Override
    public String convertToDatabaseColumn(List<WebsiteSupervisor> websiteSupervisors) {
        String websiteSupervisorJson = null;
        try {
            websiteSupervisorJson = JsonParser.serialize(websiteSupervisors);
        } catch (final JsonProcessingException e) {
            log.error("Website Supervisor JSON writing error", e);
        }

        return websiteSupervisorJson;
    }

    @Override
    public List<WebsiteSupervisor> convertToEntityAttribute(String s) {
        try {

            final ObjectMapper objectMapper = new ObjectMapper();
            WebsiteSupervisor[] websiteSupervisors = objectMapper.readValue(s,
                    WebsiteSupervisor[].class);
            return Arrays.asList(websiteSupervisors);

        } catch (final IOException e) {
            log.error("Website Supervisors JSON reading error", e);
            return Collections.emptyList();
        }
    }
}
