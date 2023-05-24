package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import java.util.List;

public interface EmployeeService {
    Integer sumSalary();

    Employee employeeMinSalary();

    Employee employeeMaxSalary();

    List<Employee> aboveAverageSalary();
}
