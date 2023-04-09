package pro.sky.collection_l1;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;

    public Employee(String lastName, String firstName){
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }

    @Override
    public String toString(){
        return "Employee: LastName = " + lastName + ", FirstName = " + firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getFirstName(), employee.getFirstName()) && Objects.equals(getLastName(), employee.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }

}
