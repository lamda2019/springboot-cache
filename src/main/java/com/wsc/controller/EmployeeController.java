package com.wsc.controller;

import com.wsc.bean.Employee;
import com.wsc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /*
    * http://localhost:8080/emp/1
    * */
    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmp(id);
        return employee;
    }

}
