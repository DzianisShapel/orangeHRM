package eu.senla.lab.pages;


import com.codeborne.selenide.Selenide;
import eu.senla.lab.pages.elements.NavigationBar;
import eu.senla.lab.pages.elements.forms.AssignLeaveForm;
import eu.senla.lab.utils.ConfigLoader;

import static com.codeborne.selenide.Condition.text;
import static eu.senla.lab.constants.Route.ASSIGN_LEAVE;


public class LeavePage extends BasePage {

    private final AssignLeaveForm assignLeaveForm = new AssignLeaveForm();

    public AssignLeaveForm getAssignLeaveForm(){
        return assignLeaveForm;
    }

    private final NavigationBar navigationBar = new NavigationBar();

    public NavigationBar getNavigationBar() {
        return navigationBar;
    }


    public  LeavePage open(){
        Selenide.open(ConfigLoader.getInstance().getBaseUri() + ASSIGN_LEAVE);
        injectCookieToBrowser();
        header.shouldHave(text("Leave"));
        return this;
    }


    public LeavePage clickSubmitButton(){
        submitButton.click();
        return this;
    }




}
