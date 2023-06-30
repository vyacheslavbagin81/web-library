package ru.skypro.lessons.springboot.weblibrary.service.salary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary.service.employee.EmployeeMapDTO;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {
    private final EmployeeRepository employeeRepository;
    private Logger logger = LoggerFactory.getLogger(SalaryServiceImpl.class);

    public SalaryServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Integer salarySum() {
        logger.info("Получаем сумму всех з/п");
    @Override
    public Integer salarySum() {
        return employeeRepository.salarySum();
    }

    @Override
    public Integer salaryAvg() {
        logger.info("Получаем среднюю всех з/п");
        return employeeRepository.salaryAvg();
    }

    @Override
    public EmployeeDTO employeeMinSalary() {
        logger.info("Получаем минимальную з/п");
        return EmployeeMapDTO.fromEmployee(employeeRepository.employeeMinSalary());
    }

    @Override
    public List<EmployeeDTO> withHighestSalary() {
        logger.info("Получаем список сотрудников с максимальной з/п");
        List<Employee> employeeList = employeeRepository.withHighestSalary();
        return EmployeeMapDTO.toEmployeeDTOList(employeeList);
    }

    @Override
    public List<EmployeeDTO> findBySalaryGreaterThan(int salary) {
        logger.info("Получаем список сотрудников с з/п больше {}", salary);
        List<Employee> employeeList = employeeRepository.findBySalaryGreaterThan(salary);
        return EmployeeMapDTO.toEmployeeDTOList(employeeList);
    }
}
