package eu.senla.lab;

import com.github.javafaker.Faker;
import eu.senla.lab.api.ApiHelper;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.pages.LoginPage;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LeaveTest {

    @Test
    public void assignLeaveRequest() throws InterruptedException {
        Employee employee = new Employee(new Faker().name().firstName(), new Faker().name().lastName(), new Faker().number().digits(4));
        int empNumber = ApiHelper.createEmployee(employee);
        new LoginPage().
                openLoginPage().
                login().
                openLeavePage().
                getNavigationBar().
                navigateToAssignLeave().
                assignLeave(employee);
        checkAssignedLeave(empNumber);
    }

    private void checkAssignedLeave(int empNumber){
        Response response = ApiHelper.getEmployeeLeaveRequest(empNumber);
        int requestTotal = response.path("meta.total");
        int respEmpNumber = response.path("data.employee[0].empNumber");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(requestTotal, "1");
        softAssert.assertEquals(respEmpNumber, empNumber);
        softAssert.assertAll();
    }
}
