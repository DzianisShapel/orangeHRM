package eu.senla.lab;

import com.github.javafaker.Faker;
import eu.senla.lab.api.EmployeeApi;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.pages.AdminPage;
import eu.senla.lab.pages.LoginPage;
import org.testng.annotations.Test;

public class FlowTest {


    @Test
    public void userFlowTest() throws InterruptedException {

        Employee employee = new Employee(new Faker().name().firstName(), null, new Faker().name().lastName(), new Faker().number().digits(4));
        EmployeeApi.addEmployee(employee);

        AdminPage adminpage =  new LoginPage().
                openLoginPage().
                login().
                openAdminPage().
                addUser().
                fillInForm(employee);
    }

}
