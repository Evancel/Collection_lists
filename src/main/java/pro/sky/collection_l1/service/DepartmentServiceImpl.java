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
    public Optional<Employee> employeeWithMaxSalaryInDepartment(int department){
        final Collection<Employee> allEmployees = employeeService.getAllEmployees();
        Optional <Employee> employeeWithMaxSalaryInDepartment = allEmployees.stream()
                .filter(e->e.getDepartment()==department)
                .max(Comparator.comparingInt(e->e.getSalary()));

        return employeeWithMaxSalaryInDepartment;
    }
    @Override
    public Optional<Employee> employeeWithMinSalaryInDepartment(int department){
        final Collection<Employee> allEmployees = employeeService.getAllEmployees();
        Optional <Employee> employeeWithMaxSalaryInDepartment = allEmployees.stream()
                .filter(e->e.getDepartment()==department)
                .min(Comparator.comparingInt(e->e.getSalary()));

        return employeeWithMaxSalaryInDepartment;
    }
    @Override
    public List<Employee> employeesByDepartment(int department){
        final Collection<Employee> allEmployees = employeeService.getAllEmployees();
        List <Employee> employeesInDepartment = allEmployees.stream()
                .filter(e->e.getDepartment()==department)
                .collect(Collectors.toList());
        return employeesInDepartment;
    }
    @Override
    public Map<Integer, List<Employee>> groupEmployeesByDepartment() {
        final Collection<Employee> allEmployees = employeeService.getAllEmployees();
        List<Employee> employees = allEmployees.stream()
                .sorted(Comparator.comparingInt(e->e.getDepartment()))
                .collect(Collectors.toList());

        return allEmployees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment()));
    }
}
