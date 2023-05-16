package eu.senla.lab.objects;

import lombok.Data;

@Data
public class Employee {

    private String firstName;
    private String middleName;
    private String lastName;
    private String employeeId;


    public Employee(String firstName, String middleName, String lastName, String employeeId) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.employeeId = employeeId;
    }
}
