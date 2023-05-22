package eu.senla.lab.pages.elements.forms;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.pages.elements.RecordTable;
;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;

public class BaseForm {

    SelenideElement employeeName = $x("//input[contains(@placeholder,'Type for hints')]");
    SelenideElement autocompleteDropdown = $x("//div[@class='oxd-autocomplete-dropdown --positon-bottom']");
    ElementsCollection autoCompleteOptions = $$x("//div[@class='oxd-autocomplete-dropdown --positon-bottom']//descendant::div");

    SelenideElement submitButton = $("[type='submit']");
    public BaseForm setEmployeeName(Employee employee) {
        try {
            employeeName.setValue(employee.getFirstName() + " " + employee.getLastName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
        autocompleteDropdown.should(appear);
        autoCompleteOptions.get(0).click();
        return this;
    }

    public RecordTable clickSubmitButton() {
        submitButton.click();
        return new RecordTable();
    }
}
