package eu.senla.lab.api;

import eu.senla.lab.api.actions.SpecBuilder;
import eu.senla.lab.objects.Employee;

import static io.restassured.RestAssured.given;

public class EmployeeApi extends SpecBuilder {

    public static void addEmployee(String firstName, String middleName ,String lastName, String employeeId){

        Employee employee = new Employee(firstName, middleName,lastName,employeeId);

        given().
                spec(getRequestSpecification())
                .co
    }
}
