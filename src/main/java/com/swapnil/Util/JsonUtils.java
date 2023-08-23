package com.swapnil.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

    public static String asJsonString(Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            String msg = "Could not convert object to Json";
            throw new IllegalArgumentException(msg, e);
        }
    }

    public static <A> A parseObject(String json, Class<A> clazz) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (IOException e) {
            String msg = "Could not parse Json to given object class";
            throw new IllegalArgumentException(msg, e);
        }
    }

}
