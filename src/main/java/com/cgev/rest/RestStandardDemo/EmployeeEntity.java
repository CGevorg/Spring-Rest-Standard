package com.cgev.rest.RestStandardDemo;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmployeeEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String someData;
    private String anotherData;


    public EmployeeEntity() {

    }

    EmployeeEntity(String someData, String anotherData) {
        this.someData = someData;
        this.anotherData = anotherData;
    }

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