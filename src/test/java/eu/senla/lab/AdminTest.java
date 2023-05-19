package eu.senla.lab;

import com.github.javafaker.Faker;
import eu.senla.lab.api.EmployeeApi;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.pages.AdminPage;
import eu.senla.lab.pages.LoginPage;
import org.testng.annotations.Test;

import java.util.List;

public class AdminTest {

    @Test
    public void addAdminUser() throws InterruptedException {
        Employee employee = new Employee(new Faker().name().firstName(), null, new Faker().name().lastName(), new Faker().number().digits(4));
        EmployeeApi.addEmployee(employee);
        new LoginPage().
                openLoginPage().
                login().
                openAdminPage().
                addUser().
                fillInForm(employee).
                checkRecordInTable(employee.getFirstName() + " " + employee.getLastName());
    }

    @Test
    public void addJobTitle() {
    //    String jobTitle = new Faker().company().profession();
        List<String> jobTitles = List.of(new Faker().company().profession(), new Faker().company().profession(), new Faker().company().profession());
        AdminPage adminPage = new LoginPage().
                openLoginPage().
                login().
                openAdminPage().
                goToJobTitles();
        jobTitles.forEach(adminPage::addJobTitle);
        jobTitles.forEach(adminPage::checkRecordInTable);
    }

}
