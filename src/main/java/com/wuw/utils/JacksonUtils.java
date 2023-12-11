package com.wuw.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class JacksonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String writeValueAsString(Object value){
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Optional<T> readValue(String value, Class<T> obj) {
        if(!isJson(value)){
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(objectMapper.readValue(value, obj));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static boolean isJson(String str) {
        try {
            objectMapper.readTree(str);
        } catch (JsonProcessingException e) {
            return false;
        }
        return true;
    }

}
