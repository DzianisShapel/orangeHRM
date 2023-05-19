package eu.senla.lab.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.pages.elements.NavigationBar;
import eu.senla.lab.utils.ConfigLoader;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static eu.senla.lab.constants.Route.*;

public class AdminPage {

    private NavigationBar navigationBar = new NavigationBar();

    SelenideElement addButton = $(".oxd-button-icon");

    SelenideElement userRoleField = $x("//label[text()='User Role']/parent::div//following-sibling::div//child::div[@class='oxd-select-text-input']");
    SelenideElement statusField = $x("//label[text()='Status']/parent::div//following-sibling::div//child::div[@class='oxd-select-text-input']");
    SelenideElement selectDropdown = $x("//div[@class='oxd-select-dropdown --positon-bottom']");
    ElementsCollection selectOptions = $$x("//div[@class='oxd-select-dropdown --positon-bottom']//descendant::span");

    SelenideElement autocompleteDropdown = $x("//div[@class='oxd-autocomplete-dropdown --positon-bottom']");

    ElementsCollection autoCompleteOptions = $$x("//div[@class='oxd-autocomplete-dropdown --positon-bottom']//descendant::div");

    SelenideElement employeeName = $x("//input[contains(@placeholder,'Type for hints')]");

    SelenideElement usernameField = $x("//label[text()='Username']/parent::div/following-sibling::div/child::input");

    SelenideElement passwordField = $x("//label[text()='Password']/parent::div/following-sibling::div/child::input");

    SelenideElement jobTitleField = $x("//label[text()='Job Title']/parent::div/following-sibling::div/child::input");

    SelenideElement confirmPasswordField = $x("//label[text()='Confirm Password']/parent::div/following-sibling::div/child::input");

    SelenideElement message = $(".oxd-toast-start");

    SelenideElement saveButton = $("[type='submit']");
    public AdminPage addUser(){
        addButton.click();
        webdriver().shouldHave(url(ConfigLoader.getInstance().getBaseUri() + SAVE_SYSTEM_USER));
        return this;
    }

    public AdminPage fillInForm(Employee employee) throws InterruptedException {
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
        usernameField.setValue(new Faker().name().username());
        String password = new Faker().internet().password(8,16,true, true);
        passwordField.setValue(password);
        confirmPasswordField.setValue(password);
        saveButton.click();
        message.should(appear, Duration.ofSeconds(10)).should(disappear);
        webdriver().shouldHave(urlContaining(VIEW_SYSTEM_USERS), Duration.ofSeconds(10));
        return this;
    }

    public AdminPage checkRecordInTable(String value){
        $x("//div[text()='" + value + "']").should(exist);
        return this;
    }

    public AdminPage goToJobTitles(){
        getNavigationBar().getJobTab().click();
        getNavigationBar().jobTitleOption().click();
        webdriver().shouldHave(urlContaining(VIEW_JOB_TITLE_LIST), Duration.ofSeconds(10));
        return this;
    }

    public NavigationBar getNavigationBar() {
        return navigationBar;
    }

    public AdminPage addJobTitle(String jobTitle) {
        addButton.click();
        webdriver().shouldHave(urlContaining(SAVE_JOB_TITLE), Duration.ofSeconds(10));
        jobTitleField.setValue(jobTitle);
        saveButton.click();
        webdriver().shouldHave(urlContaining(VIEW_JOB_TITLE_LIST), Duration.ofSeconds(10));
        return this;
    }
}
