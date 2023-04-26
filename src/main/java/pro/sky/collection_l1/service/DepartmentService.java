package pro.sky.collection_l1.service;

import pro.sky.collection_l1.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {
    Optional<Employee> employeeWithMaxSalaryInDepartment(int department);
    Optional<Employee> employeeWithMinSalaryInDepartment(int department);
    List<Employee> employeesByDepartment(int department);
    Map<Integer, List<Employee>> groupEmployeesByDepartment();
}
