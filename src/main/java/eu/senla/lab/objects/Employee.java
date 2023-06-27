package eu.senla.lab.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data
public class Employee {

    private String firstName;
    private String lastName;
    private String employeeId;
    @JsonIgnore
    private String email;


    public Employee(String firstName, String lastName, String employeeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
    }
}
