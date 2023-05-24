package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.List;
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
    private final List<Employee> employeeList = List.of(
            new Employee("Petr", 120000),
            new Employee("Ivan", 95000),
            new Employee("Stepan", 50000),
            new Employee("Olga", 80000),
            new Employee("Elena", 95000)
    );

    @Override
    public List<Employee> getAllEmploees() {
        return employeeList;
    }
}
