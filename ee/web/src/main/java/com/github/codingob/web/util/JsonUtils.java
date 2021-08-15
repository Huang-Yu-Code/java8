package com.github.codingob.web.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJson(Object o) {
        String json = null;
        try {
            json = MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static <T> T toObject(String json, Class<T> object) {
        T o = null;
        try {
            o = MAPPER.readValue(json, object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return o;
    }
}
