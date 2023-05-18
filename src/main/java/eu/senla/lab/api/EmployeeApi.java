package eu.senla.lab.api;

import eu.senla.lab.api.actions.ApiRequests;
import eu.senla.lab.api.actions.SpecBuilder;
import eu.senla.lab.objects.Employee;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class EmployeeApi extends SpecBuilder {

    public static void addEmployee(Employee employee){
        given().
                spec(getRequestSpecification()).
                contentType(ContentType.JSON).
                cookie(ApiRequests.getAuthCookie()).
                body(employee).
        when().
                post("/api/v2/pim/employees").
        then().spec(getResponseSpecification()).
                statusCode(200);
    }
}
