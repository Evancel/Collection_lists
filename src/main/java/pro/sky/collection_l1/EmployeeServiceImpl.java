package pro.sky.collection_l1;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList;
    public EmployeeServiceImpl(){
        this.employeeList = new ArrayList<>();
    }
    private final static int MAX_SIZE_OF_LIST = 3;

    public Employee addEmployee(String firstName, String lastName) {
        //блок проверок на ошибки
        if(firstName.equals(null) || firstName=="" || lastName.equals(null) || lastName=="" ) {
            throw new BadParamsException();
        }  else if (employeeList.size()>MAX_SIZE_OF_LIST){
            throw new EmployeeStorageIsFullException();
        }

        Employee e = new Employee(firstName, lastName);
        if (employeeList.contains(e)){
            throw new EmployeeAlreadyAddedException();
        } else {
            employeeList.add(e);
            System.out.println("New employee has been added successfully.");
            return e;
        }
    }

    public Employee removeEmployee (String firstName, String lastName) {
        //блок проверок на ошибки
        if(firstName.equals(null) || firstName=="" || lastName.equals(null) || lastName=="" ) {
            throw new BadParamsException();
        } else if (findEmployee(firstName, lastName).equals(null)){
            throw new EmployeeNotFoundException();
        } else {
            Employee e = findEmployee(firstName, lastName);
            employeeList.remove(e);
            System.out.println("The employee has been deleted successfully.");

            return e;
        }
    }

    public Employee findEmployee(String firstName, String lastName) {

        if(firstName.equals(null) || firstName=="" || lastName.equals(null) || lastName=="" ) {
            throw new BadParamsException();
        }

        Employee e = new Employee(firstName, lastName);

        if(employeeList.contains(e)){
            return e;
        } else{
            throw new EmployeeNotFoundException();
        }
    }

    public Collection<Employee> showAllEmployees() {
      return Collections.unmodifiableList(employeeList);
    }
}
