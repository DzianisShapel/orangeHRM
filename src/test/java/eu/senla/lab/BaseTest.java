package eu.senla.lab;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import eu.senla.lab.api.ApiHelper;
import eu.senla.lab.objects.Employee;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Browsers.FIREFOX;

public class BaseTest {

    private RemoteWebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional String browser) throws MalformedURLException {
        switch (browser) {
            case "chrome":
                Configuration.browser = CHROME;
        //        Configuration.browserSize = "1280x720";
                ChromeOptions options = new ChromeOptions();
                options.setCapability("selenoid:options", new HashMap<String, Object>() {{
      //              put("enableVideo", true);
                    put("enableVNC", true);
                }});
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                break;
            case "firefox":
                Configuration.browser = FIREFOX;
        //        Configuration.browserSize = "1280x720";
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability("browserVersion", "112.0");
                firefoxOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
      //              put("enableVideo", true);
                    put("enableVNC", true);
                }});
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
                break;
        }
        WebDriverRunner.setWebDriver(driver);
        WebDriverRunner.getWebDriver().manage().window().maximize();
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
