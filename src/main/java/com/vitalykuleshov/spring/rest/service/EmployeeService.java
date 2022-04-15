package com.vitalykuleshov.spring.rest.service;

import com.vitalykuleshov.spring.rest.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployees();
    public void saveEmployee(Employee employe);
    public Employee getEmployee(int id);
    public void deleteEmployee(int id);
}
