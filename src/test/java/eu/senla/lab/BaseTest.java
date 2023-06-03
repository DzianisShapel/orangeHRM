package eu.senla.lab;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import eu.senla.lab.api.ApiHelper;
import eu.senla.lab.objects.Employee;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional String browser) throws MalformedURLException {
        switch (browser) {
            case "chrome":
            case "firefox":
                final DesiredCapabilities caps = new DesiredCapabilities();
                caps.setBrowserName(browser);
                caps.setCapability("enableVNC", true);
                RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
                WebDriverRunner.setWebDriver(driver);
                break;
        }
    }

    public Employee createEmployee() {
        Employee employee = new Employee(new Faker().name().firstName(), new Faker().name().lastName(), new Faker().number().digits(4));
        ApiHelper.createEmployee(employee);
        return employee;
    }

    @AfterMethod
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }
}
