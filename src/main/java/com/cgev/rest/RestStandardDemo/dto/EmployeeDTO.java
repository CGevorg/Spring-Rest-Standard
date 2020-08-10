package com.cgev.rest.RestStandardDemo.dto;

import com.cgev.rest.RestStandardDemo.util.MessageKey;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EmployeeDTO {

    private Integer id;
    @NotEmpty(message = MessageKey.PROPERTY_NOT_EMPTY)
    private String someData;
    @NotEmpty(message = MessageKey.PROPERTY_NOT_EMPTY)
    private String anotherData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSomeData() {
        return someData;
    }

    public void setSomeData(String someData) {
        this.someData = someData;
    }

    public String getAnotherData() {
        return anotherData;
    }

    public void setAnotherData(String anotherData) {
        this.anotherData = anotherData;
    }
}
