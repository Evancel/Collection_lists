package pro.sky.collection_l1.service;

import pro.sky.collection_l1.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Integer maxSalaryOfDepartment(int department);
    Integer minSalaryOfDepartment(int department);
    int sumSalaryOfDepartment(int department);
    List<Employee> employeesByDepartment(int department);
    Map<Integer, List<Employee>> groupEmployeesByDepartment();
}
