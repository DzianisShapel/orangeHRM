package eu.senla.lab.pages.elements.forms;

import com.codeborne.selenide.SelenideElement;
import eu.senla.lab.pages.elements.RecordTable;


import static com.codeborne.selenide.Selenide.*;

public class EmployeeInformationForm extends BaseForm {

    SelenideElement employeeId = $x("//label[contains(text(),'Employee')]/parent::div/following-sibling::div/input");

    public EmployeeInformationForm setEmployeeId(String id){
        employeeId.setValue(id);
        return this;
    }
    /*public RecordTable clickSubmitButton() {
        submitButton.click();
        return new RecordTable();
    }*/
}
