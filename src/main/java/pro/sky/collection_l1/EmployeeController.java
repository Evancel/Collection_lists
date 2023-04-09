package pro.sky.collection_l1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService service){
        this.employeeService = service;
    }

    @GetMapping
    public String sayHello(){
        return "Hello, world";
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam (name="firstName", required = false) String firstName,
                            @RequestParam (name = "lastName", required = false) String lastName){

    return employeeService.addEmployee(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam (name="firstName", required = false) String firstName,
                              @RequestParam (name = "lastName", required = false) String lastName){
     return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam (name="firstName", required = false) String firstName,
                                 @RequestParam (name = "lastName", required = false) String lastName){

    return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping()
    public Collection<Employee> showAllEmployees(){
        return employeeService.showAllEmployees();
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
