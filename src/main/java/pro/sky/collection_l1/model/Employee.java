package pro.sky.collection_l1.model;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private int department;
    private int salary;

    public Employee(String lastName, String firstName){
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public Employee(String lastName, String firstName, int department, int salary){
        this.lastName = lastName;
        this.firstName = firstName;
        this.department = department;
        this.salary=salary;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public int getDepartment() {
        return department;
    }
    public int getSalary() {
        return salary;
    }

    @Override
    public String toString(){
        return "Employee: LastName = " + lastName + ", FirstName = " + firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && salary == employee.salary && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, department, salary);
    }
}
