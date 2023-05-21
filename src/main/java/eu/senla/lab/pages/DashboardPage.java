package eu.senla.lab.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;


public class DashboardPage extends BasePage {


        SelenideElement adminButton = $x("//span[text()='Admin']");
        SelenideElement recruitmentButton = $x("//span[text()='Recruitment']");
        SelenideElement leaveButton = $x("//span[text()='Leave']");

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

}
