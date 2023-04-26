package pro.sky.collection_l1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.collection_l1.model.Employee;
import pro.sky.collection_l1.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService service){
        this.departmentService = service;
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeWithMaxSalaryInDepartment
            (@RequestParam(name="departmentId", required = false) int department){
        return departmentService.employeeWithMaxSalaryInDepartment(department).orElseThrow();
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeWithMinSalaryInDepartment
            (@RequestParam(name="departmentId", required = false) int department){
        return departmentService.employeeWithMinSalaryInDepartment(department).orElseThrow();
    }

    @GetMapping("/all")
    public List<Employee> getEmployeesByDepartment
            (@RequestParam(name="departmentId") int department){
        return departmentService.employeesByDepartment(department);
    }

    @GetMapping("/all/collected-by-department")
    public Map<Integer, List<Employee>> groupEmployeesByDepartment(){

        return departmentService.groupEmployeesByDepartment();
    }
}
