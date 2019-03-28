package com.chainfire.parsers.helpers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class JSON {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T fromJson(String jsonString, Class<T> clazz) throws IOException {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonString, clazz);
    }

    public static String toJson(Object o) throws IOException {
        Writer writer = new StringWriter();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.writeValue(writer, o);
        return writer.toString();
    }

    public static String toJsonSafe(Object o) {
        String v = null;
        try {
            v = toJson(o);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }

    public static String toJsonPretty(Object o) {
        Writer writer = new StringWriter();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(writer, o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}
