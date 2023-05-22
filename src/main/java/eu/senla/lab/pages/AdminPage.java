package eu.senla.lab.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.objects.JobTitle;
import eu.senla.lab.pages.elements.NavigationBar;
import eu.senla.lab.utils.ConfigLoader;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static eu.senla.lab.constants.Route.*;

public class AdminPage extends BasePage {

    private NavigationBar navigationBar = new NavigationBar();

    SelenideElement userRoleField = $x("//label[text()='User Role']/parent::div//following-sibling::div//child::div[@class='oxd-select-text-input']");
    SelenideElement statusField = $x("//label[text()='Status']/parent::div//following-sibling::div//child::div[@class='oxd-select-text-input']");
    SelenideElement selectDropdown = $x("//div[@class='oxd-select-dropdown --positon-bottom']");
    ElementsCollection selectOptions = $$x("//div[@class='oxd-select-dropdown --positon-bottom']//descendant::span");

    SelenideElement autocompleteDropdown = $x("//div[@class='oxd-autocomplete-dropdown --positon-bottom']");

    ElementsCollection autoCompleteOptions = $$x("//div[@class='oxd-autocomplete-dropdown --positon-bottom']//descendant::div");

    SelenideElement employeeName = $x("//input[contains(@placeholder,'Type for hints')]");

    SelenideElement usernameField = $x("//label[text()='Username']/parent::div/following-sibling::div/child::input");

    SelenideElement usernameHint = $x("//label[text()='Username']/parent::div/following-sibling::span");

    SelenideElement passwordField = $x("//label[text()='Password']/parent::div/following-sibling::div/child::input");

    SelenideElement passwordHint = $x("//label[text()='Password']/parent::div/following-sibling::span");

    SelenideElement jobTitleField = $x("//label[text()='Job Title']/parent::div/following-sibling::div/child::input");

    SelenideElement confirmPasswordField = $x("//label[text()='Confirm Password']/parent::div/following-sibling::div/child::input");

    SelenideElement confirmPasswordHint = $x("//label[text()='Confirm Password']/parent::div/following-sibling::span");
    SelenideElement message = $(".oxd-toast-start");


    public AdminPage addUser(){
        addButton.click();
        webdriver().shouldHave(url(ConfigLoader.getInstance().getBaseUri() + SAVE_SYSTEM_USER));
        return this;
    }

    public AdminPage fillFormWithCorrectData(Employee employee, String password) throws InterruptedException {
        userRoleField.click();
        selectDropdown.should(appear);
        selectOptions.get(0).click();
        statusField.click();
        selectDropdown.should(appear);
        selectOptions.get(0).click();
        employeeName.setValue(employee.getFirstName() + " " + employee.getLastName());
        Thread.sleep(2000);
        autocompleteDropdown.should(appear);
        autoCompleteOptions.get(0).click();
        setUsernameField(new Faker().name().username()).
        setPasswordField(password).
        setConfirmPasswordField(password).
        clickSubmitButton();
        message.should(appear, Duration.ofSeconds(10)).should(disappear);
        webdriver().shouldHave(urlContaining(VIEW_SYSTEM_USERS), Duration.ofSeconds(10));
        return this;
    }

    public AdminPage setUsernameField(String username){
        usernameField.setValue(username);
        return this;
    }

    public AdminPage setPasswordField(String password){
        passwordField.setValue(password);
        return this;
    }

    public AdminPage setConfirmPasswordField(String password){
        confirmPasswordField.setValue(password);
        return this;
    }

    public AdminPage clickSubmitButton() {
        submitButton.click();
        return this;
    }


    public AdminPage checkRecordInTable(String value){
        $x("//div[text()='" + value + "']").should(exist);
        return this;
    }

    public NavigationBar goToNavigationBar() {
        return navigationBar;
    }

    public AdminPage addJobTitle(String jobTitle) {
        addButton.click();
        webdriver().shouldHave(urlContaining(SAVE_JOB_TITLE), Duration.ofSeconds(10));
        jobTitleField.setValue(jobTitle);
        submitButton.click();
        webdriver().shouldHave(urlContaining(VIEW_JOB_TITLE_LIST), Duration.ofSeconds(10));
        return this;
    }

    public AdminPage deleteJobTitle(JobTitle jobTitle){
        $x("//div[text()='" + jobTitle.getTitle() + "']/parent::div/following-sibling::div[2]/descendant::i[@class='oxd-icon bi-trash']").shouldBe(interactable).click();
        $x("//*[text()=' Yes, Delete ']").click();
        spinner.should(appear).should(disappear);
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
}
