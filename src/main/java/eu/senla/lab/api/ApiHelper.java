package eu.senla.lab.api;

import eu.senla.lab.api.actions.AuthHelper;
import eu.senla.lab.api.actions.SpecBuilder;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.objects.JobTitle;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelper extends SpecBuilder {

    public static int createEmployee(Employee employee){
        return given().
                spec(getRequestSpecification()).
                contentType(ContentType.JSON).
                cookie(AuthHelper.getAuthCookie()).
                body(employee).
        when().
                post("/api/v2/pim/employees").
        then().spec(getResponseSpecification()).
                statusCode(200).
                extract().
                body().
                path("data.empNumber");
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

    public static Response getEmployeeLeaveRequest(int number){
        LocalDate localDate = LocalDate.now();
        String localDateString = localDate.format(DateTimeFormatter.ISO_DATE);

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("empNumber", String.valueOf(number));
        queryParams.put("fromDate", localDateString);
        queryParams.put("toDate", localDateString);
        queryParams.put("includeEmployees", "onlyCurrent");
        queryParams.put("statuses[]", "3");

        return given().
                spec(getRequestSpecification()).
                contentType(ContentType.JSON).
                cookie(AuthHelper.getAuthCookie()).
                queryParams(queryParams).
        when().
                get("/api/v2/leave/employees/leave-requests").
        then().spec(getResponseSpecification()).
                statusCode(200).
                extract().
                response();
    }

}
