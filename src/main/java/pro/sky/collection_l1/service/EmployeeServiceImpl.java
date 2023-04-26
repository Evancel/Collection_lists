package pro.sky.collection_l1.service;

import org.springframework.stereotype.Service;
import pro.sky.collection_l1.model.Employee;
import pro.sky.collection_l1.exception.BadParamsException;
import pro.sky.collection_l1.exception.EmployeeAlreadyAddedException;
import pro.sky.collection_l1.exception.EmployeeNotFoundException;
import pro.sky.collection_l1.exception.EmployeeStorageIsFullException;

import java.util.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList;
    public EmployeeServiceImpl(){
        this.employeeList = new ArrayList<>();
    }
    private final static int MAX_SIZE_OF_LIST = 3;

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, int salary) {
        //блок проверок на ошибки
        if(firstName==null || firstName.isEmpty() || lastName==null || lastName.isEmpty() ) {
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
        if(firstName==null || firstName.isEmpty() || lastName==null || lastName.isEmpty() ) {
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

        if(firstName.equals(null) || firstName=="" || lastName.equals(null) || lastName=="" ) {
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

}
