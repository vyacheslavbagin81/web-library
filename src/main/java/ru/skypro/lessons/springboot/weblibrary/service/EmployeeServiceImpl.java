package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.exceptions.ExceptionNoId;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeRepository getRepository() {
        return repository;
    }


    @Override
    public void addEmployees(EmployeeDTO employee) {
        repository.save(EmployeeMapDTO.toEmployee(employee));
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        repository.findAll().forEach(employeeList::add);
        return EmployeeMapDTO.toEmployeeDTOList(employeeList);
    }

    @Override
    public void editEmployee(EmployeeDTO employeeDTO) throws ExceptionNoId {
        int id = employeeDTO.getId();
        Employee result = repository.findById(id)
                .orElseThrow(ExceptionNoId::new);
        if (!employeeDTO.getName().isBlank()) {
            result.setName(employeeDTO.getName());
        }
        if (employeeDTO.getSalary() > 0) {
            result.setSalary(employeeDTO.getSalary());
        }
        repository.save(result);
    }

    @Override
    public EmployeeDTO getEmployeeToId(int id) throws ExceptionNoId {
        return repository.findById(id)
                .map(EmployeeMapDTO::fromEmployee)
                .orElseThrow(ExceptionNoId::new);
    }

    @Override
    public List<EmployeeFullInfo> getAllEmployeeFullInfo() {
        return repository.getEmployeeFullInfo();
    }

    @Override
    public EmployeeFullInfo getAllEmployeeToIdFullInfo(int id) throws ExceptionNoId {
        return repository.findById(id)
                .map(EmployeeMapDTO::toEmployeeFullInfo)
                .orElseThrow(ExceptionNoId::new);
    }

    @Override
    public List<EmployeeDTO> getEmployeeByPositionName(String position) {
        String pos = position.toLowerCase();
        List<Employee> employeeList = repository.findEmployeeByPosition_Name(pos);
        if (!position.isBlank()) {
            return EmployeeMapDTO.toEmployeeDTOList(employeeList);
        } else return getAllEmployee();
    }

    @Override
    public List<EmployeeDTO> getEmployeeFromPage(int page) {
        return repository.findAll(PageRequest.of(page, 10))
                .stream().map(EmployeeMapDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployeeToId(int id) throws ExceptionNoId {
        if(repository.existsById(id)){
        repository.deleteById(id);
        } else throw new ExceptionNoId();
    }

    @Override
    public Integer salarySum() {
        return repository.salarySum();
    }

    @Override
    public Integer salaryAvg() {
        return repository.salaryAvg();
    }

    @Override
    public EmployeeDTO employeeMinSalary() {
        return EmployeeMapDTO.fromEmployee(repository.employeeMinSalary());
    }

    @Override
    public List<EmployeeDTO> withHighestSalary() {
        List<Employee> employeeList = repository.withHighestSalary();
        return EmployeeMapDTO.toEmployeeDTOList(employeeList);
    }

    @Override
    public List<EmployeeDTO> findBySalaryGreaterThan(int salary) {
        List<Employee> employeeList = repository.findBySalaryGreaterThan(salary);
        return EmployeeMapDTO.toEmployeeDTOList(employeeList);
    }
}
