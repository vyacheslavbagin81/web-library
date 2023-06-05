package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.exceptions.ExceptionNoId;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    Integer sumSalary();

    Employee employeeMinSalary();

    Employee employeeMaxSalary();

    List<Employee> aboveAverageSalary();

    void addEmployees(Employee employee);

    void editEmployee(Employee employee, int id) throws ExceptionNoId;

    Employee printEmployeeToId(int id) throws ExceptionNoId;

    void deleteEmployeeToId(int id) throws ExceptionNoId;

    List<Employee> salaryHigherThan(int salary);
}
