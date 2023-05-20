package pro.sky.collection_l1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.collection_l1.exception.BadParamsException;
import pro.sky.collection_l1.exception.EmployeeAlreadyAddedException;
import pro.sky.collection_l1.exception.EmployeeNotFoundException;
import pro.sky.collection_l1.exception.EmployeeStorageIsFullException;
import pro.sky.collection_l1.model.Employee;
import pro.sky.collection_l1.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService service){
        this.employeeService = service;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam (name = "lastName", required = false) String lastName,
                                @RequestParam (name="firstName", required = false) String firstName,
                                @RequestParam (name = "departmentId", required = false) int department,
                                @RequestParam (name = "salary", required = false) int salary){

    return employeeService.addEmployee(lastName,firstName,department,salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam (name = "lastName", required = false) String lastName,
                                   @RequestParam (name="firstName", required = false) String firstName){
     return employeeService.removeEmployee(lastName,firstName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam (name = "lastName", required = false) String lastName,
                                 @RequestParam (name="firstName", required = false) String firstName){

    return employeeService.findEmployee(lastName,firstName);
    }

    @GetMapping("/showAll")
    public Collection<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }


    @ExceptionHandler
    public ResponseEntity<String> handleEmployeeNotFoundedException (EmployeeNotFoundException exc){
        return new ResponseEntity<>("ERROR.The employee doesn't exist in the list.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleBadParamsException (BadParamsException exc){
        return new ResponseEntity<>("ERROR. Incorrect input requestParam: firstName or lastName.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleEmployeeAlreadyAddedException (EmployeeAlreadyAddedException exc){
        return new ResponseEntity<>("ERROR. The employee has already existed in the List.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleEmployeeStorageIsFullException (EmployeeStorageIsFullException exc){
        return new ResponseEntity<>("ERROR. There is no more free space in the List.", HttpStatus.BAD_REQUEST);
    }


}
