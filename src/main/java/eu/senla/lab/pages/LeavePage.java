package eu.senla.lab.pages;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import eu.senla.lab.pages.elements.NavigationBar;
import eu.senla.lab.pages.elements.forms.AssignLeaveForm;
import eu.senla.lab.utils.ConfigLoader;

import static com.codeborne.selenide.Condition.text;
import static eu.senla.lab.constants.Route.*;


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
        Selenide.open(ConfigLoader.getInstance().getBaseUri() + LOGIN);
        injectCookieToBrowser();
        Selenide.refresh();
        WebDriverRunner.getWebDriver().navigate().to(ConfigLoader.getInstance().getBaseUri() + ASSIGN_LEAVE);
        header.shouldHave(text("Leave"));
        return this;
    }
}
