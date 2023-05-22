package eu.senla.lab.pages;

import com.codeborne.selenide.SelenideElement;
import eu.senla.lab.objects.JobTitle;
import eu.senla.lab.pages.elements.NavigationBar;
import eu.senla.lab.pages.elements.forms.AddUserForm;
import eu.senla.lab.utils.ConfigLoader;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static eu.senla.lab.constants.Route.*;

public class AdminPage extends BasePage {

    private NavigationBar navigationBar = new NavigationBar();
    private AddUserForm addUserForm = new AddUserForm();

    SelenideElement jobTitleField = $x("//label[text()='Job Title']/parent::div/following-sibling::div/child::input");

    public AddUserForm clickAddUserButton(){
        addButton.click();
        webdriver().shouldHave(url(ConfigLoader.getInstance().getBaseUri() + SAVE_SYSTEM_USER));
        return addUserForm;
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


}
