package com.cgev.rest.RestStandardDemo.service;


import com.cgev.rest.RestStandardDemo.dto.EmployeeDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest // will initialize entire application
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService service;

    private EmployeeDTO testEmployee;


    @Before
    public void init() {
        //Initializing Test Employee object
        testEmployee = new EmployeeDTO();
        testEmployee.setSomeData("Some Data");
        testEmployee.setAnotherData("Another Data");


    }

    @Test
    public void addEmployee() {
        EmployeeDTO employee = service.addEmployee(testEmployee);
        assertThat(employee).isEqualToIgnoringGivenFields(testEmployee, "id");
    }

    @Test
    public void getEmployeeAfterAdding(){
        EmployeeDTO employee = service.addEmployee(testEmployee);
        EmployeeDTO getEmployee = service.getEmployee(employee.getId());
        assertThat(getEmployee).isEqualToComparingFieldByField(employee);
    }
}
