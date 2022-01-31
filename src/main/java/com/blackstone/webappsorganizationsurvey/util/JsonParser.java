package com.blackstone.webappsorganizationsurvey.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonParser {


    /**
     * Private Default constructor to prevent initialization from this class
     */
    private JsonParser() {
    }

    /**
     * Object with JSON
     *
     * @param object to serialize
     * @return json string (Not pretty, use toJson(object, pretty)
     * @throws JsonProcessingException if a problem occurs
     */
    public static String serialize(Object object) throws JsonProcessingException {
        return serialize(object, false);
    }

    /**
     * Object with JSON with pretty print feature
     *
     * @param object to serialize
     * @param pretty to format the json
     * @return json string
     * @throws JsonProcessingException if a problem occurs
     */
    public static String serialize(Object object, boolean pretty) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        if (pretty) {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
        }
        return mapper.writeValueAsString(object);
    }


    /**
     * Object from JSON
     *
     * @param json      string
     * @param jsonClass the class of the Pojo to construct
     * @return an instance of the Pojo
     * @throws IOException if a problem occurs
     */
    public static <T> T deserialize(String json, Class<T> jsonClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.readValue(json, jsonClass);
    }

    /**
     * Object from JSON
     *
     * @param json      string
     * @param jsonClass the class of the Pojo to construct
     * @return an instance of the Pojo
     * @throws IOException if a problem occurs
     */
    public static <T> T deserialize(String json, TypeReference<T> jsonClass) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.readValue(json, jsonClass);
    }

}
