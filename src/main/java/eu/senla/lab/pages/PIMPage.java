package eu.senla.lab.pages;

import eu.senla.lab.pages.elements.forms.EmployeeInformationForm;

public class PIMPage extends BasePage {

    private final EmployeeInformationForm employeeInformationForm = new EmployeeInformationForm();

    public EmployeeInformationForm getEmployeeInformationForm(){
        return employeeInformationForm;
    }


}
