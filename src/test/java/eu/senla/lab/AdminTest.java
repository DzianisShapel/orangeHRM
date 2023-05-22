package eu.senla.lab;

import com.github.javafaker.Faker;
import eu.senla.lab.api.ApiHelper;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.pages.LoginPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;

import static com.codeborne.selenide.Selenide.webdriver;

public class AdminTest {

    @DataProvider(name = "passwordData")
    public Iterator<Object[]> createPasswordData() {
        return Arrays.asList(
                new Object[]{"1",  "Should have at least 7 characters"},
                new Object[]{"12345678", "Your password must contain minimum 1 lower-case letter"}
        ).iterator();
    }

    @AfterMethod
    public void closeBrowser(){
        webdriver().driver().getWebDriver().quit();
    }


    @Test
    public void addAdminUser() throws InterruptedException {
        Employee employee = new Employee(new Faker().name().firstName(), new Faker().name().lastName(), new Faker().number().digits(4));
        String password = new Faker().internet().password(8,16,true, true);
        ApiHelper.createEmployee(employee);
        new LoginPage().
                openLoginPage().
                login().
                openAdminPage().
                addUser().
                fillFormWithCorrectData(employee, password).
                checkRecordInTable(employee.getFirstName() + " " + employee.getLastName());
    }

    @Test
    public void allFieldsRequired() {
        int totalRequiredFields = new LoginPage().
                openLoginPage().
                login().
                openAdminPage().
                addUser().
                clickSubmitButton().
                getRequiredFields();
        Assertions.assertThat(totalRequiredFields).isEqualTo(6);
    }

    @Test(dataProvider = "passwordData")
    public void validatePasswordField(String password, String expectedHint){
        String actualHint = new LoginPage().
                openLoginPage().
                login().
                openAdminPage().
                addUser().
                setPasswordField(password).
                getPasswordHint();

        Assertions.assertThat(actualHint).isEqualTo(expectedHint);
    }

    @Test
    public void validateConfirmPasswordField(){
        String password = "qwerty123";
        String actualHint = new LoginPage().
                openLoginPage().
                login().
                openAdminPage().
                addUser().
                setPasswordField(password).
                setConfirmPasswordField(password.substring(2,4)).
                getConfirmPasswordHint();
        Assertions.assertThat(actualHint).isEqualTo("Passwords do not match");
    }

    @Test
    public void validateUsernameField(){
        String username = "user";
        String actualHint = new LoginPage().
                openLoginPage().
                login().
                openAdminPage().
                addUser().setUsernameField(username).getUsernameHint();
        Assertions.assertThat(actualHint).isEqualTo("Should be at least 5 characters");
    }

}
