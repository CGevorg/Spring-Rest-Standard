package com.cgev.rest.RestStandardDemo.repository;

import com.cgev.rest.RestStandardDemo.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
}
