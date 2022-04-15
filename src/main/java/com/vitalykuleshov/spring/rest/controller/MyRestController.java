package com.vitalykuleshov.spring.rest.controller;

import com.vitalykuleshov.spring.rest.entity.Employee;
import com.vitalykuleshov.spring.rest.exception_handling.EmployeeIncorrectData;
import com.vitalykuleshov.spring.rest.exception_handling.NoSuchEmployeeException;
import com.vitalykuleshov.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees(){
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);

        if(employee == null){
            throw new NoSuchEmployeeException("There is no employee with ID: " + id);
        }
        return employee;
    }

    @PostMapping("/employees")
   public Employee addNewEloyee(@RequestBody Employee employee){

        employeeService.saveEmployee(employee);

        return employee;
   }
   @PutMapping("/employees")
   public Employee updateEmployee(@RequestBody Employee employee){

        employeeService.saveEmployee(employee);
        return employee;
   }
    @DeleteMapping ("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);
        if (employee == null){
            throw new NoSuchEmployeeException("Trere is no employee with ID: " + id + " in Database");
        }
        employeeService.deleteEmployee(id);
        return "Employee with ID: " + id + "was deleted";
    }

}
