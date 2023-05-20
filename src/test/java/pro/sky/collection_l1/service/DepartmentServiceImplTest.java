package pro.sky.collection_l1.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.collection_l1.model.Employee;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @Mock
    private EmployeeServiceImpl employeeServiceMock;
    @InjectMocks
    private DepartmentServiceImpl departmentService;

    public List<Employee> createEmployeesList(){
        List< Employee> employees = new ArrayList<>();
        employees.add(new Employee("Petrov","Petr",1,100000));
        employees.add(new Employee("ivanov","Ivan",1,170000));
        employees.add(new Employee("poTaPOV","Sergey",1,130000));
        employees.add(new Employee("Petrova","OLGA",1,165000));
        employees.add(new Employee("Semenov","oleg",2,70000));
        employees.add(new Employee("ANDREEV","ANDREY",2,65000));
        employees.add(new Employee("Sidorova","Anastasia",3,110000));
        employees.add(new Employee("Ivanova","Katerina",3,95000));
        employees.add(new Employee("Andreeva","Irina",3,210000));
        employees.add(new Employee("Losev","Kirill",4,650000));

        return employees;
    }

    @Test
    public void checkEmployeesByDepartment(){
        assertNotNull(employeeServiceMock);

        List<Employee> employees = createEmployeesList();

        int department=1;
        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee("Petrov","Petr",1,100000));
        expected.add(new Employee("ivanov","Ivan",1,170000));
        expected.add(new Employee("poTaPOV","Sergey",1,130000));
        expected.add(new Employee("Petrova","OLGA",1,165000));

        Mockito.when(employeeServiceMock.getAllEmployees()).thenReturn(employees);
        List<Employee> actual = departmentService.employeesByDepartment(department);
        assertEquals(expected,actual);
    }

    @Test
    public void checkEmployeesByNoDepartment(){
        assertNotNull(employeeServiceMock);

        List<Employee> employees = createEmployeesList();

        int department=8;

        Mockito.when(employeeServiceMock.getAllEmployees()).thenReturn(employees);
        List<Employee> actual = departmentService.employeesByDepartment(department);
        List<Employee> expected = new ArrayList<>();
        assertEquals(expected,actual);
    }

    @Test
    public void checkMaxSalaryOfDepartment(){

        List<Employee> employees = createEmployeesList();

        int department=1;

        Mockito.when(employeeServiceMock.getAllEmployees()).thenReturn(employees);
        Integer actual = departmentService.maxSalaryOfDepartment(department);
        int expected = 170000;
        assertEquals(expected,actual);
    }

    @Test
    public void checkMaxSalaryOfNoDepartment(){

        List<Employee> employees = createEmployeesList();

        int department=11;

        Mockito.when(employeeServiceMock.getAllEmployees()).thenReturn(employees);
        assertThrows(RuntimeException.class,() -> departmentService.maxSalaryOfDepartment(department));
    }

    @Test
    public void checkMinSalaryOfDepartment(){

        List<Employee> employees = createEmployeesList();

        int department=3;

        Mockito.when(employeeServiceMock.getAllEmployees()).thenReturn(employees);
        Integer actual = departmentService.minSalaryOfDepartment(department);
        int expected = 95000;
        assertEquals(expected,actual);
    }

    @Test
    public void checkSumSalaryOfDepartment(){

        List<Employee> employees = createEmployeesList();

        int department=2;

        Mockito.when(employeeServiceMock.getAllEmployees()).thenReturn(employees);
        int actual = departmentService.sumSalaryOfDepartment(department);
        int expected = 135000;
        assertEquals(expected,actual);
    }

    @Test
    public void checkSumSalaryOfNoDepartment(){

        List<Employee> employees = createEmployeesList();

        int department=20;

        Mockito.when(employeeServiceMock.getAllEmployees()).thenReturn(employees);
        int actual = departmentService.sumSalaryOfDepartment(department);
        int expected = 0;
        assertEquals(expected,actual);
    }

    @Test
    public void checkGroupEmployeesByDepartment(){

        List<Employee> employees = createEmployeesList();

        Map<Integer, List<Employee>> expected = new HashMap<>();
        expected.put(1,List.of((new Employee("Petrov","Petr",1,100000))
                ,(new Employee("ivanov","Ivan",1,170000))
                ,(new Employee("poTaPOV","Sergey",1,130000))
                ,(new Employee("Petrova","OLGA",1,165000))));
        expected.put(2,List.of((new Employee("Semenov","oleg",2,70000))
                ,(new Employee("ANDREEV","ANDREY",2,65000))));
        expected.put(3,List.of((new Employee("Sidorova","Anastasia",3,110000))
                ,(new Employee("Ivanova","Katerina",3,95000))
                ,(new Employee("Andreeva","Irina",3,210000))));
        expected.put(4,List.of((new Employee("Losev","Kirill",4,650000))));

        Mockito.when(employeeServiceMock.getAllEmployees()).thenReturn(employees);
        Map<Integer, List<Employee>> actual = departmentService.groupEmployeesByDepartment();
        assertEquals(expected,actual);
    }
}
