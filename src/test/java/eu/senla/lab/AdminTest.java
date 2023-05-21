package eu.senla.lab;

import com.github.javafaker.Faker;
import eu.senla.lab.api.ApiHelper;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.pages.LoginPage;
import org.testng.annotations.Test;

public class AdminTest {

    @Test
    public void addAdminUser() throws InterruptedException {
        Employee employee = new Employee(new Faker().name().firstName(), new Faker().name().lastName(), new Faker().number().digits(4));
        ApiHelper.createEmployee(employee);
        new LoginPage().
                openLoginPage().
                login().
                openAdminPage().
                addUser().
                fillFormWithCorrectData(employee).
                submitForm().
                checkRecordInTable(employee.getFirstName() + " " + employee.getLastName());
    }
}
