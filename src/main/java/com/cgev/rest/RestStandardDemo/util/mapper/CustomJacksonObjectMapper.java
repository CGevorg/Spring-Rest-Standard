package com.cgev.rest.RestStandardDemo.util.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CustomJacksonObjectMapper implements CustomObjectMapper {

    private final ObjectMapper objectMapper;

    public CustomJacksonObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return objectMapper.convertValue(fromValue, toValueType);
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
