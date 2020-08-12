package com.cgev.rest.RestStandardDemo.util.mapper;

import org.springframework.stereotype.Component;

@Component
public class AnotherObjectMapper implements CustomObjectMapper{
    @Override
    public <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return null;
    }
}
