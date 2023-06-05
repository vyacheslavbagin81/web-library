package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final Map<Integer, Employee> employeeMap = new HashMap<>();


    public EmployeeRepositoryImpl() {
        employeeMap.put(1, new Employee("Petr", 120000));
        employeeMap.put(2, new Employee("Ivan", 95000));
        employeeMap.put(3, new Employee("Stepan", 50000));
        employeeMap.put(4, new Employee("Olga", 80000));
        employeeMap.put(5, new Employee("Elena", 95000));
    }

    @Override
    public Map<Integer, Employee> getAllEmploees() {
        return employeeMap;
    }
}
