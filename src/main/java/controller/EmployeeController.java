package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Employee;
import service.EmployeeService;

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

}
