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
    /*
    *
    *  http://localhost:8080/emp?id=1&lastName=jack&email=jack@qq.com&gender=1&dId=1
    * */
    @GetMapping("/emp")
    public Employee update(Employee employee){
        Employee emp = employeeService.updateEmp(employee);

        return emp;
    }

   /*
   *  http://localhost:8080/delete?id=1
   * */
    @GetMapping("/delete")
    public String deleteEmp(Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }


}
