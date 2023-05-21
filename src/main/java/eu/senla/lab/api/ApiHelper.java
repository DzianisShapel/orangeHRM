package eu.senla.lab.api;

import eu.senla.lab.api.actions.AuthHelper;
import eu.senla.lab.api.actions.SpecBuilder;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.objects.JobTitle;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class ApiHelper extends SpecBuilder {

    public static void createEmployee(Employee employee){
        given().
                spec(getRequestSpecification()).
                contentType(ContentType.JSON).
                cookie(AuthHelper.getAuthCookie()).
                body(employee).
        when().
                post("/api/v2/pim/employees").
        then().spec(getResponseSpecification()).
                statusCode(200);
    }

    public static void addJobTitle(JobTitle jobTitle){
        given().
                spec(getRequestSpecification()).
                contentType(ContentType.JSON).
                cookie(AuthHelper.getAuthCookie()).
                body(jobTitle).
        when().
                post("/api/v2/admin/job-titles").
                then().spec(getResponseSpecification()).
                statusCode(200);
    }

}
