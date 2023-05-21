package eu.senla.lab.pages;


import com.codeborne.selenide.SelenideElement;
import eu.senla.lab.objects.Employee;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static eu.senla.lab.constants.Route.ADD_CANDIDATE;


public class RecruitmentPage extends BasePage {

    SelenideElement firstNameField = $(".orangehrm-firstname");
    SelenideElement lastNameField = $(".orangehrm-lastname");
    SelenideElement emailField = $x("//*[text()='Email']/parent::div/following-sibling::div/input");

    SelenideElement candidateProfile = $x("//h6[text()='Candidate Profile']");

    public RecruitmentPage addCandidate(Employee employee){
        addButton.click();
        webdriver().shouldHave(urlContaining(ADD_CANDIDATE), Duration.ofSeconds(10));
        firstNameField.setValue(employee.getFirstName());
        lastNameField.setValue(employee.getLastName());
        emailField.setValue(employee.getEmail());
        submitButton.click();
        spinner.should(appear).should(disappear);
        candidateProfile.should(appear);
        return this;
    }

    public RecruitmentPage checkCandidateProfile(Employee employee){
        firstNameField.shouldHave(value(employee.getFirstName()));
        lastNameField.shouldHave(value(employee.getLastName()));
        emailField.shouldHave(value(employee.getEmail()));
        return this;
    }

}
