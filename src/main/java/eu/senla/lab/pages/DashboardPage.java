package eu.senla.lab.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {


        SelenideElement AdminButton = $x("//span[text()='Admin']");
        SelenideElement header = $(".oxd-topbar-header-breadcrumb-module");


        public AdminPage openAdminPage(){
            AdminButton.click();
            header.shouldHave(text("Admin"));
            return new AdminPage();
        }

}
