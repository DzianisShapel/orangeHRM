package eu.senla.lab.pages;

import com.codeborne.selenide.SelenideElement;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.pages.elements.NavigationBar;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LeavePage extends BasePage {


    SelenideElement leaveStatusField = $x("//label[contains(text(), 'Status')]/parent::div/following-sibling::div/descendant::div[@class='oxd-select-text-input']");
    SelenideElement leaveTypeField = $(".oxd-select-text-input");
    SelenideElement calendarField = $("div.oxd-date-input > input");
    SelenideElement calendar = $(".oxd-date-input-calendar");
    SelenideElement todayDate = $(".--today");

    SelenideElement searchButton = $x("//button[normalize-space()='Search']");




    private NavigationBar navigationBar = new NavigationBar();

    public NavigationBar getNavigationBar() {
        return navigationBar;
    }

    public LeavePage assignLeave(Employee employee) throws InterruptedException {
        leaveTypeField.click();
        selectDropdown.should(appear);
        selectOptions.get(0).click();
        employeeName.setValue(employee.getFirstName() + " " + employee.getLastName());
        Thread.sleep(2000);
        autocompleteDropdown.should(appear);
        autoCompleteOptions.get(0).click();
        calendarField.click();
        calendar.should(appear);
        todayDate.click();
        submitButton.click();
        modalConfirmButton.should(appear, Duration.ofSeconds(10)).click();
        return this;
    }

    public LeavePage checkAssignedLeave(Employee employee) throws InterruptedException {
        employeeName.setValue(employee.getFirstName() + " " + employee.getLastName());
        Thread.sleep(2000);
        autocompleteDropdown.should(appear);
        autoCompleteOptions.get(0).click();
        leaveStatusField.click();
        autocompleteDropdown.should(appear);
        autoCompleteOptions.get(3).click();
        searchButton.click();
        $x("//div[text()='" + employee.getFirstName() + " " + employee.getLastName()  + "']").should(exist);
        return this;
    }

}
