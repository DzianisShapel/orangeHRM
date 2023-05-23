package eu.senla.lab.pages.elements.forms;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import eu.senla.lab.objects.Employee;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AssignLeaveForm extends BaseForm {

    SelenideElement leaveTypeField = $(".oxd-select-text-input");
    SelenideElement calendarField = $("div.oxd-date-input > input");
    SelenideElement calendar = $(".oxd-date-input-calendar");
    SelenideElement todayDate = $(".--today");
    ElementsCollection errorHints = $$(".oxd-input-field-error-message");

    public AssignLeaveForm assignLeave(Employee employee) {
        setLeaveType().
        setCalendar().
        setEmployeeName(employee);
        clickAssignButton();
        modalConfirmButton.should(appear, Duration.ofSeconds(10)).click();
        return this;
    }

    private AssignLeaveForm setLeaveType(){
        leaveTypeField.click();
        selectDropdown.should(appear);
        selectOptions.get(0).click();
        return this;
    }

    private AssignLeaveForm setCalendar(){
        calendarField.click();
        calendar.should(appear);
        todayDate.click();
        return this;
    }

    public AssignLeaveForm clickAssignButton() {
        submitButton.click();
        return this;
    }

    public int getRequiredFields(){
        return errorHints.size();
    }
}
