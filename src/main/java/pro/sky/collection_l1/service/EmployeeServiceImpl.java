package pro.sky.collection_l1.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.collection_l1.model.Employee;
import pro.sky.collection_l1.exception.BadParamsException;
import pro.sky.collection_l1.exception.EmployeeAlreadyAddedException;
import pro.sky.collection_l1.exception.EmployeeNotFoundException;
import pro.sky.collection_l1.exception.EmployeeStorageIsFullException;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList;
    public EmployeeServiceImpl(){
        this.employeeList = new ArrayList<>();
    }
    private final static int MAX_SIZE_OF_LIST = 3;

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, int salary) {

        if(!validationName(firstName, lastName)) {
            throw new BadParamsException();
        }  else if (employeeList.size()>MAX_SIZE_OF_LIST){
            throw new EmployeeStorageIsFullException();
        }

        Employee e = new Employee(firstName, lastName, department, salary);
        if (employeeList.contains(e)){
            throw new EmployeeAlreadyAddedException();
        }

        employeeList.add(e);
        System.out.println("New employee has been added successfully.");
        return e;

    }
    @Override
    public Employee removeEmployee (String firstName, String lastName) {
        //блок проверок на ошибки
        if(!validationName(firstName, lastName)) {
            throw new BadParamsException();
        } else if (findEmployee(firstName, lastName).equals(null)){
            throw new EmployeeNotFoundException();
        }

        Employee e = findEmployee(firstName, lastName);
        employeeList.remove(e);
        System.out.println("The employee has been deleted successfully.");

        return e;

    }
    @Override
    public Employee findEmployee(String firstName, String lastName) {

        if(!validationName(firstName, lastName)) {
            throw new BadParamsException();
        }

        Employee e = new Employee(firstName, lastName);

        if(!employeeList.contains(e)){
            throw new EmployeeNotFoundException();
        }

        return e;
    }
    @Override
    public Collection<Employee> getAllEmployees() {
      return Collections.unmodifiableList(employeeList);
    }

    public boolean validationName(String firstName, String lastName){
        return (firstName!=null && !isBlank(firstName)
                && lastName!=null && !isBlank(lastName)
                && isAlphaSpace(firstName) && isAlphaSpace(lastName)
                && !equalsIgnoreCase(trim(firstName), trim(lastName)));
    }

}
