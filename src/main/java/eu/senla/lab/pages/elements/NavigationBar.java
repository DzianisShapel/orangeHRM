package eu.senla.lab.pages.elements;

import com.codeborne.selenide.SelenideElement;
import eu.senla.lab.pages.AdminPage;
import eu.senla.lab.pages.LeavePage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static eu.senla.lab.constants.Route.*;

public class NavigationBar {

    private final SelenideElement jobTab = $x("//span[contains(text(), 'Job')]");

    private final SelenideElement jobTitleOption = $x("//a[text()='Job Titles']");

    private final SelenideElement assignLeaveTab = $x("//a[text()='Assign Leave']");


    public AdminPage navigateToJobTitles(){
        jobTab.click();
        jobTitleOption.click();
        webdriver().shouldHave(urlContaining(VIEW_JOB_TITLE_LIST), Duration.ofSeconds(10));
        return new AdminPage();
    }

    public LeavePage navigateToAssignLeave(){
        assignLeaveTab.click();
        webdriver().shouldHave(urlContaining(ASSIGN_LEAVE), Duration.ofSeconds(10));
        return new LeavePage();
    }

}
