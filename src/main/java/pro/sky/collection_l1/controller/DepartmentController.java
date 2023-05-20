package pro.sky.collection_l1.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.collection_l1.model.Employee;
import pro.sky.collection_l1.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService service){
        this.departmentService = service;
    }

    @GetMapping("/{id}/salary/max")
    public Integer maxSalaryOfDepartment
            (@PathVariable("id") int department){
        return departmentService.maxSalaryOfDepartment(department);
    }

    @GetMapping("/{id}/salary/min")
    public Integer minSalaryOfDepartment
            (@PathVariable("id") int department){
        return departmentService.minSalaryOfDepartment(department);
    }

    @GetMapping("/{id}/salary/sum")
    public int getSumSalaryOfDepartment
            (@PathVariable("id") int department){
        return departmentService.sumSalaryOfDepartment(department);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByDepartment
            (@PathVariable("id") int department){
        return departmentService.employeesByDepartment(department);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> groupEmployeesByDepartment(){

        return departmentService.groupEmployeesByDepartment();
    }
}
