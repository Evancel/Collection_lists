package pro.sky.collection_l1.service;

import org.springframework.stereotype.Service;
import pro.sky.collection_l1.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;
    public DepartmentServiceImpl(EmployeeService service){
        this.employeeService = service;
    }

    
    @Override
    public Integer maxSalaryOfDepartment(int department){
        final Collection<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees.stream()
                .filter(e->e.getDepartment()==department)
                .map(Employee::getSalary)
                .max(Integer::compareTo)
                .orElseThrow(()->new RuntimeException("There are no employee in the department!"));
    }
    @Override
    public Integer minSalaryOfDepartment(int department){
        final Collection<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees.stream()
                .filter(e->e.getDepartment()==department)
                .map(Employee::getSalary)
                .min(Integer::compareTo)
                .orElseThrow(()->new RuntimeException("There are no employee in the department!"));
    }

    @Override
    public int sumSalaryOfDepartment(int department){
        final Collection<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees.stream()
                .filter(e->e.getDepartment()==department)
                .mapToInt(Employee::getSalary)
                .sum();
    }
    @Override
    public List<Employee> employeesByDepartment(int department){
        final Collection<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees.stream()
                .filter(e->e.getDepartment()==department)
                .collect(Collectors.toList());
    }
    @Override
    public Map<Integer, List<Employee>> groupEmployeesByDepartment() {
        final Collection<Employee> allEmployees = employeeService.getAllEmployees();
        List<Employee> employees = allEmployees.stream()
                .sorted(Comparator.comparingInt(Employee::getDepartment))
                .collect(Collectors.toList());

        return allEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
