package eu.senla.lab.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;


public class DashboardPage extends BasePage {

        SelenideElement adminButton = $x("//span[text()='Admin']");
        SelenideElement recruitmentButton = $x("//span[text()='Recruitment']");
        SelenideElement leaveButton = $x("//span[text()='Leave']");
        SelenideElement pimButton = $x("//span[text()='PIM']");
        ElementsCollection dashboardElements = $$("div.orangehrm-dashboard-widget-name > p.oxd-text--p");


        public AdminPage openAdminPage(){
            adminButton.click();
            header.shouldHave(text("Admin"));
            return new AdminPage();
        }

        public RecruitmentPage openRecruitmentPage(){
            recruitmentButton.click();
            header.shouldHave(text("Recruitment"));
            return new RecruitmentPage();
        }

        public LeavePage openLeavePage(){
            leaveButton.click();
            header.shouldHave(text("Leave"));
            return new LeavePage();
        }

        public PIMPage openPIMPage(){
            pimButton.click();
            header.shouldHave(text("PIM"));
            return new PIMPage();
        }

        public List<String> getDashboardElements(){
            return dashboardElements.texts();
        }

}
