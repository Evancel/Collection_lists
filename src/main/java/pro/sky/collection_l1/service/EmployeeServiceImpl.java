package pro.sky.collection_l1.service;

import org.springframework.stereotype.Service;
import pro.sky.collection_l1.model.Employee;
import pro.sky.collection_l1.exception.BadParamsException;
import pro.sky.collection_l1.exception.EmployeeAlreadyAddedException;
import pro.sky.collection_l1.exception.EmployeeNotFoundException;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList;
    public EmployeeServiceImpl(){
        this.employeeList = new ArrayList<>();
    }
  //  private final static int MAX_SIZE_OF_LIST = 3;

    @Override
    public Employee addEmployee(String lastName, String firstName,int department, int salary) {

        if(!validationName(lastName, firstName)) {
            throw new BadParamsException();
        }
  //      else if (employeeList.size()>MAX_SIZE_OF_LIST){
  //          throw new EmployeeStorageIsFullException();
  //      }

        Employee e = new Employee(lastName, firstName,department, salary);
        if (employeeList.contains(e)){
            throw new EmployeeAlreadyAddedException();
        }

        employeeList.add(e);
        System.out.println("New employee has been added successfully.");
        return e;

    }
    @Override
    public Employee removeEmployee (String lastName, String firstName) {
        //блок проверок на ошибки
        if(!validationName(lastName,firstName)) {
            throw new BadParamsException();
        } else if (findEmployee(lastName,firstName).equals(null)){
            throw new EmployeeNotFoundException();
        }

        Employee e = findEmployee(lastName,firstName);
        employeeList.remove(e);
        System.out.println("The employee has been deleted successfully.");

        return e;

    }
    @Override
    public Employee findEmployee(String lastName,String firstName) {

        if(!validationName(lastName,firstName)) {
            throw new BadParamsException();
        }

        Employee e = new Employee(lastName,firstName);

        if(!employeeList.contains(e)){
            throw new EmployeeNotFoundException();
        }

        return e;
    }
    @Override
    public Collection<Employee> getAllEmployees() {
      return Collections.unmodifiableList(employeeList);
    }

    public boolean validationName(String lastName,String firstName){
        return (firstName!=null && !isBlank(firstName)
                && lastName!=null && !isBlank(lastName)
                && isAlphaSpace(firstName) && isAlphaSpace(lastName)
                && !equalsIgnoreCase(trim(firstName), trim(lastName)));
    }

}
