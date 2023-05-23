package eu.senla.lab.pages.elements.forms;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.pages.elements.RecordTable;


import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static eu.senla.lab.constants.Route.VIEW_SYSTEM_USERS;

public class AddUserForm extends BaseForm {

    SelenideElement userRoleField = $x("//label[text()='User Role']/parent::div//following-sibling::div//child::div[@class='oxd-select-text-input']");
    SelenideElement statusField = $x("//label[text()='Status']/parent::div//following-sibling::div//child::div[@class='oxd-select-text-input']");
    SelenideElement usernameField = $x("//label[text()='Username']/parent::div/following-sibling::div/child::input");

    SelenideElement usernameHint = $x("//label[text()='Username']/parent::div/following-sibling::span");

    SelenideElement passwordField = $x("//label[text()='Password']/parent::div/following-sibling::div/child::input");

    SelenideElement passwordHint = $x("//label[text()='Password']/parent::div/following-sibling::span");

    SelenideElement confirmPasswordField = $x("//label[text()='Confirm Password']/parent::div/following-sibling::div/child::input");

    SelenideElement confirmPasswordHint = $x("//label[text()='Confirm Password']/parent::div/following-sibling::span");
    SelenideElement message = $(".oxd-toast-start");

    public RecordTable fillFormWithCorrectData(Employee employee, String password) throws InterruptedException {
        setUserRoleField().
        setUserStatus().
        setUsernameField(new Faker().name().username()).
        setPasswordField(password).
        setConfirmPasswordField(password).
        setEmployeeName(employee);
        clickSubmitButton();
        message.should(appear, Duration.ofSeconds(10)).should(disappear);
        webdriver().shouldHave(urlContaining(VIEW_SYSTEM_USERS), Duration.ofSeconds(10));
        return new RecordTable();
    }

    public AddUserForm setUserRoleField(){
        userRoleField.click();
        selectDropdown.should(appear);
        selectOptions.get(0).click();
        return this;
    }

    public AddUserForm setUserStatus(){
        statusField.click();
        selectDropdown.should(appear);
        selectOptions.get(0).click();
        return this;
    }

    public AddUserForm setUsernameField(String username){
        usernameField.setValue(username);
        return this;
    }

    public AddUserForm setPasswordField(String password){
        passwordField.setValue(password);
        return this;
    }

    public AddUserForm setConfirmPasswordField(String password){
        confirmPasswordField.setValue(password);
        return this;
    }

    public int getRequiredFields() {
        return $$x("//span[text()='Required']").size();
    }

    public String getPasswordHint(){
        return passwordHint.should(appear).text();
    }

    public String getConfirmPasswordHint() {
        return confirmPasswordHint.text();
    }


    public String getUsernameHint(){
        return usernameHint.text();
    }

    public AddUserForm setFieldsEmptyAndClick(){
        submitButton.click();
        return this;
    }

/*    public RecordTable clickSubmitButton() {
        submitButton.click();
        return new RecordTable();
    }*/
}
