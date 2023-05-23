package eu.senla.lab;

import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import eu.senla.lab.api.ApiHelper;
import eu.senla.lab.api.actions.AuthHelper;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.utils.CookieUtils;
import io.restassured.http.Cookie;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.webdriver;

public class BaseTest {


    public Employee createEmployee(){
        Employee employee = new Employee(new Faker().name().firstName(), new Faker().name().lastName(), new Faker().number().digits(4));
        ApiHelper.createEmployee(employee);
        return employee;
    }

   /* @BeforeMethod
    public void injectCookieToBrowser(){
       Cookie cookie = AuthHelper.getAuthCookie();
       org.openqa.selenium.Cookie selCookie = CookieUtils.convertRestAssuredToSeleniumCookie(cookie);
       WebDriverRunner.getWebDriver().manage().addCookie(selCookie);
    }*/

    @AfterMethod
    public void closeBrowser(){
        webdriver().driver().getWebDriver().quit();
    }
}
