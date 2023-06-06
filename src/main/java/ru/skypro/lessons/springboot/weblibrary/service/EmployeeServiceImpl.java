package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.exceptions.ExceptionNoId;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Integer sumSalary() {
        int sumSalary = 0;
        List<Integer> salaryList = repository.getAllEmploees()
                .values()
                .stream()
                .map(Employee::getSalary)
                .toList();
        for (Integer salary : salaryList) {
            sumSalary = sumSalary + salary;
        }
        return sumSalary;
    }

    @Override
    public Employee employeeMinSalary() {
        return repository.getAllEmploees()
                .values()
                .stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow();
    }

    @Override
    public Employee employeeMaxSalary() {
        return repository.getAllEmploees()
                .values()
                .stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow();
    }

    @Override
    public List<Employee> aboveAverageSalary() {
        int sizeRepository = repository.getAllEmploees().size();
        int averageSalary = sumSalary() / sizeRepository;
        return repository.getAllEmploees()
                .values()
                .stream()
                .filter(employee -> employee.getSalary() > averageSalary)
                .collect(Collectors.toList());
    }

    @Override
    public void addEmployees(Employee employee) {
        int id = repository.getAllEmploees().size();
        repository.getAllEmploees().put(id, employee);
    }

    @Override
    public void editEmployee(Employee employee, int id) throws ExceptionNoId {
        checkId(id);
        repository.getAllEmploees()
                .replace(id, employee);
    }

    @Override
    public Employee printEmployeeToId(int id) throws ExceptionNoId {
        checkId(id);
        return repository.getAllEmploees().get(id);
    }

    @Override
    public void deleteEmployeeToId(int id) throws ExceptionNoId {
        checkId(id);
        repository.getAllEmploees().remove(id);
    }

    @Override
    public List<Employee> salaryHigherThan(int salary) {
        return repository.getAllEmploees()
                .values()
                .stream()
                .filter(employee -> employee.getSalary() > salary)
                .collect(Collectors.toList());
    }

    private void checkId(int id) throws ExceptionNoId {
        if (!repository.getAllEmploees().containsKey(id)) {
            throw new ExceptionNoId();
        }
    }

}
