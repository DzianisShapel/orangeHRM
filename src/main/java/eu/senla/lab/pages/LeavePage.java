package eu.senla.lab.pages;

import com.codeborne.selenide.SelenideElement;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.pages.elements.NavigationBar;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

public class LeavePage extends BasePage {


    SelenideElement leaveTypeField = $(".oxd-select-text-input");
    SelenideElement calendarField = $("div.oxd-date-input > input");
    SelenideElement calendar = $(".oxd-date-input-calendar");
    SelenideElement todayDate = $(".--today");




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
        calendarField.click();
        calendar.should(appear);
        todayDate.click();
        submitButton.click();
        modalConfirmButton.should(appear, Duration.ofSeconds(10)).click();
        return this;
    }

}
