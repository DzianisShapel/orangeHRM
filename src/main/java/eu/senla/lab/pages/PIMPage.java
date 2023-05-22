package eu.senla.lab.pages;

import eu.senla.lab.pages.elements.forms.EmployeeInformationForm;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;

public class PIMPage extends BasePage {

    private EmployeeInformationForm employeeInformationForm = new EmployeeInformationForm();

    public EmployeeInformationForm getEmployeeInformationForm(){
        return employeeInformationForm;
    }

    public PIMPage checkRecordInTable(String value){
        $x("//*[text()='" + value + "']").should(exist);
        return this;
    }

}
