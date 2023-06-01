package eu.senla.lab;

import com.codeborne.selenide.Selenide;
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
import java.util.ArrayList;
import java.util.HashMap;

public class BaseTest {

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional String browser) throws MalformedURLException {
        switch (browser) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.setCapability("browserVersion", "113.0");
                options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                    /* How to add test badge*/
                    put("name", "Test badge...");
                    /* How to set session timeout */
                    put("sessionTimeout", "15m");
                    /* How to set timezone */
                    put("env", new ArrayList<String>() {{
                        add("TZ=UTC");
                    }});
                    /* How to add "trash" button */
                    put("labels", new HashMap<String, Object>() {{
                        put("manual", "true");
                    }});
                    /* How to enable video recording */
                    put("enableVideo", true);
                }});
                RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability("browserVersion", "113.0");
                firefoxOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
                    /* How to add test badge */
                    put("name", "Test badge...");
                    /* How to set session timeout */
                    put("sessionTimeout", "15m");
                    /* How to set timezone */
                    put("env", new ArrayList<String>() {{
                        add("TZ=UTC");
                    }});
                    /* How to add "trash" button */
                    put("labels", new HashMap<String, Object>() {{
                        put("manual", "true");
                    }});
                    /* How to enable video recording */
                    put("enableVideo", true);
                }});
                RemoteWebDriver fdriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
                break;
        }
    }


    public Employee createEmployee(){
        Employee employee = new Employee(new Faker().name().firstName(), new Faker().name().lastName(), new Faker().number().digits(4));
        ApiHelper.createEmployee(employee);
        return employee;
    }

    @AfterMethod
    public void closeBrowser(){
        Selenide.closeWebDriver();
    }
}
