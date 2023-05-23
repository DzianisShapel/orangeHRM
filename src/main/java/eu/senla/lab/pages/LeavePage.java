package eu.senla.lab.pages;


import eu.senla.lab.pages.elements.NavigationBar;
import eu.senla.lab.pages.elements.forms.AssignLeaveForm;



public class LeavePage extends BasePage {

    private AssignLeaveForm assignLeaveForm = new AssignLeaveForm();

    public AssignLeaveForm getAssignLeaveForm(){
        return assignLeaveForm;
    }

    private NavigationBar navigationBar = new NavigationBar();

    public NavigationBar getNavigationBar() {
        return navigationBar;
    }



    public LeavePage clickSubmitButton(){
        submitButton.click();
        return this;
    }




}
