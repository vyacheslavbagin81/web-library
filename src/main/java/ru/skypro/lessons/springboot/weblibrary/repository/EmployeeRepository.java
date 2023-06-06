package ru.skypro.lessons.springboot.weblibrary.repository;

import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.Map;

public interface EmployeeRepository {
    Map<Integer, Employee> getAllEmploees();

}
