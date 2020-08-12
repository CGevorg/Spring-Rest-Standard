package com.cgev.rest.RestStandardDemo.util.mapper;

public interface CustomObjectMapper {
    <T> T convertValue(Object fromValue, Class<T> toValueType);
}
