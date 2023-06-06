package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary.exceptions.ExceptionNoId;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/salary/sum")
    public Integer salarySum() {
        return employeeService.sumSalary();
    }

    @GetMapping("/salary/min")
    public Employee salaryMin() {
        return employeeService.employeeMinSalary();
    }

    @GetMapping("/salary/max")
    public Employee salaryMax() {
        return employeeService.employeeMaxSalary();
    }

    @GetMapping("/high-salary")
    public List<Employee> highSalary() {
        return employeeService.aboveAverageSalary();
    }

    @PostMapping("employees")
    void addEmployees(@RequestBody Employee employee) {
        employeeService.addEmployees(employee);
    }

    @PutMapping("employees/{id}")
    void editEmployee(@RequestBody Employee employee, @PathVariable int id) throws ExceptionNoId {
        employeeService.editEmployee(employee, id);
    }

    @GetMapping("employees/{id}")
    Employee printEmployeeToId(@PathVariable int id) throws ExceptionNoId {
        return employeeService.printEmployeeToId(id);
    }

    @DeleteMapping("employees/{id}")
    void deleteEmployeeToId(@PathVariable int id) throws ExceptionNoId {
        employeeService.deleteEmployeeToId(id);
    }

    @GetMapping("employees/salaryHigherThan/")
    List<Employee> salaryHigherThan(@RequestParam("salary") int salary) {
        return employeeService.salaryHigherThan(salary);
    }
}
