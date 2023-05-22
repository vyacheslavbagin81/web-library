package service;

import pojo.Employee;
import repository.EmployeeRepository;

import java.util.List;

public interface EmployeeService {
    Integer sumSalary();

    Employee employeeMinSalary();

    Employee employeeMaxSalary();

    List<Employee> aboveAverageSalary();
}
