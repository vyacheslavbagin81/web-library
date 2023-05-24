package service;

import pojo.Employee;
import java.util.List;

public interface EmployeeService {
    Integer sumSalary();

    Employee employeeMinSalary();

    Employee employeeMaxSalary();

    List<Employee> aboveAverageSalary();
}
