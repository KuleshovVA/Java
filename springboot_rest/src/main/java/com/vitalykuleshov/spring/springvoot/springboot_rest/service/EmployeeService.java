package com.vitalykuleshov.spring.springvoot.springboot_rest.service;



import com.vitalykuleshov.spring.springvoot.springboot_rest.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployees();
    public void saveEmployee(Employee employe);
    public Employee getEmployee(int id);
    public void deleteEmployee(int id);
}
