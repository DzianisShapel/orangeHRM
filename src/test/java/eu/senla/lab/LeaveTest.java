package eu.senla.lab;

import com.github.javafaker.Faker;
import eu.senla.lab.api.ApiHelper;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.pages.LeavePage;
import eu.senla.lab.pages.LoginPage;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LeaveTest extends BaseTest {

    @Test
    public void assignLeaveRequest() {
        Employee employee = new Employee(new Faker().name().firstName(), new Faker().name().lastName(), new Faker().number().digits(4));
        int empNumber = ApiHelper.createEmployee(employee);
        new LoginPage().
                openLoginPage().
                login().
                openLeavePage().
                getNavigationBar().
                navigateToAssignLeave().
                getAssignLeaveForm().
                assignLeave(employee);
        checkAssignedLeave(empNumber);
    }

    @Test
    public void validateAssignLeaveForm(){
        int num = new LeavePage().open().
                getAssignLeaveForm().
                clickAssignButton().
                getRequiredFields();
        Assertions.assertThat(num).isEqualTo(4);
    }


    private void checkAssignedLeave(int empNumber){
        Response response = ApiHelper.getEmployeeLeaveRequest(empNumber);
        int requestTotal = response.path("meta.total");
        int respEmpNumber = response.path("data.employee[0].empNumber");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(requestTotal, 1);
        softAssert.assertEquals(respEmpNumber, empNumber);
        softAssert.assertAll();
    }
}
