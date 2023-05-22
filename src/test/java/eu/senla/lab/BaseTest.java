package eu.senla.lab;

import com.github.javafaker.Faker;
import eu.senla.lab.api.ApiHelper;
import eu.senla.lab.objects.Employee;
import org.testng.annotations.AfterMethod;

import static com.codeborne.selenide.Selenide.webdriver;

public class BaseTest {


    public Employee createEmployee(){
        Employee employee = new Employee(new Faker().name().firstName(), new Faker().name().lastName(), new Faker().number().digits(4));
        ApiHelper.createEmployee(employee);
        return employee;
    }

    @AfterMethod
    public void closeBrowser(){
        webdriver().driver().getWebDriver().quit();
    }
}
