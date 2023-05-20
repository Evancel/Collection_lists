package pro.sky.collection_l1.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.collection_l1.exception.BadParamsException;
import pro.sky.collection_l1.exception.EmployeeAlreadyAddedException;
import pro.sky.collection_l1.exception.EmployeeNotFoundException;
import pro.sky.collection_l1.model.Employee;

import java.util.stream.Stream;

public class EmployeeServiceImplTest {
private final EmployeeService out = new EmployeeServiceImpl();

public static Stream<Arguments> employeesData(){
    return Stream.of(
            Arguments.of("Petrov","Petr",1,100000),
            Arguments.of("ivanov","Ivan",1,170000),
            Arguments.of("poTaPOV","Sergey",1,130000),
            Arguments.of("Petrova","OLGA",1,165000),
            Arguments.of("Semenov","oleg",2,70000),
            Arguments.of("ANDREEV","ANDREY",2,65000),
            Arguments.of("Sidorova","Anastasia",3,110000),
            Arguments.of("Ivanova","Katerina",3,95000),
            Arguments.of("Andreeva","Irina",3,210000),
            Arguments.of("Losev","Kirill",4,650000));
}

    @ParameterizedTest
    @MethodSource("employeesData")
    public void checkAddEmployeeSuccessfully(String lastName,String firstName,int department, int salary){
        Employee actual = out.addEmployee(lastName, firstName, department, salary);
        Employee expected = new Employee(lastName, firstName, department, salary);

        Assertions.assertEquals(expected,actual);
}

    @Test
    public void checkAddEmployeeThrowEmployeeAlreadyAddedException (){
        String firstName = "Petr";
        String lastName = "Petrov";
        int department = 1;
        int salary = 100000;

        Employee actual = out.addEmployee(lastName, firstName, department, salary);
        Employee expected = new Employee(lastName, firstName, department, salary);

        Assertions.assertEquals(expected,actual);
        Assertions.assertThrows(EmployeeAlreadyAddedException.class,
                ()-> out.addEmployee(lastName, firstName, department, salary));
    }

    @Test
    public void checkAddEmployeeThrowBadParamsException (){
        String firstName = "Petr";
        String lastName = null;
        int department = 1;
        int salary = 100000;

        Assertions.assertThrows(BadParamsException.class,
                ()-> out.addEmployee(lastName, firstName, department, salary));
    }

    @ParameterizedTest
    @MethodSource("employeesData")
    public void checkFindEmployeeSuccessfully(String lastName,String firstName,int department, int salary){
        out.addEmployee(lastName, firstName, department, salary);
        Employee e = new Employee(lastName, firstName,department, salary);

        Employee actualFounded = out.findEmployee(lastName, firstName);
        Assertions.assertEquals(e,actualFounded);
    }

    @ParameterizedTest
    @MethodSource("employeesData")
    public void checkFindEmployeeThrowEmployeeNotFoundException(String lastName,String firstName,int department, int salary){
        out.addEmployee(lastName, firstName, department, salary);

        String lastNameTest = "Potter";
        String firstNameTest = "Harry";
        int departmentTest = 6;
        int salaryTest=100;

        Assertions.assertThrows(EmployeeNotFoundException.class, ()->out.findEmployee(lastNameTest,firstNameTest));
    }

    @ParameterizedTest
    @MethodSource("employeesData")
    public void checkFindEmployeeMixedThrowEmployeeNotFoundException(String lastName,String firstName,int department, int salary){
        out.addEmployee(lastName, firstName, department, salary);

        String lastNameTest = firstName;
        String firstNameTest = lastName;
        int departmentTest = 6;
        int salaryTest=100;
        Assertions.assertThrows(EmployeeNotFoundException.class, ()->out.findEmployee(lastNameTest,firstNameTest));
    }

    @ParameterizedTest
    @MethodSource("employeesData")
    public void checkRemoveEmployeeSuccessfully(String lastName,String firstName,int department, int salary){
        out.addEmployee(lastName, firstName, department, salary);
        Employee e = new Employee(lastName, firstName,department, salary);

        Employee actualFounded = out.removeEmployee(lastName, firstName);
        Assertions.assertEquals(e,actualFounded);
    }

    @ParameterizedTest
    @MethodSource("employeesData")
    public void checkRemoveEmployeeThrowEmployeeNotFoundException(String lastName,String firstName,int department, int salary){
        out.addEmployee(lastName, firstName, department, salary);

        String lastNameTest = "Potter";
        String firstNameTest = "Harry";
        int departmentTest = 6;
        int salaryTest=100;

        Employee empTest = new Employee(lastNameTest,firstNameTest,departmentTest,salaryTest);
        Assertions.assertThrows(EmployeeNotFoundException.class, ()->out.findEmployee(lastNameTest,firstNameTest));
    }

}
