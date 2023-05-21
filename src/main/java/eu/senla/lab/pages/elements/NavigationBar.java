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

    private SelenideElement jobTab = $x("//span[contains(text(), 'Job')]");

    private SelenideElement jobTitleOption = $x("//a[text()='Job Titles']");

    private SelenideElement assignLeaveTab = $x("//a[text()='Assign Leave']");
    private SelenideElement leaveListTab = $x("//a[text()='Leave List']");

    public SelenideElement getJobTab() {
        return jobTab;
    }

    public SelenideElement jobTitleOption() {
        return jobTitleOption;
    }


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

    public LeavePage navigateToLeaveList(){
        leaveListTab.click();
        webdriver().shouldHave(urlContaining(VIEW_LEAVE_LIST), Duration.ofSeconds(10));
        return new LeavePage();
    }


}
