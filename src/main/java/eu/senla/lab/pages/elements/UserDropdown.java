package eu.senla.lab.pages.elements;

import com.codeborne.selenide.SelenideElement;
import eu.senla.lab.pages.LoginPage;
import eu.senla.lab.utils.ConfigLoader;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static eu.senla.lab.constants.Route.LOGIN;
import static eu.senla.lab.constants.Route.SAVE_SYSTEM_USER;

public class UserDropdown {

    SelenideElement dropdownTab = $(".oxd-userdropdown-tab");
    SelenideElement logout = $x("//a[text()='Logout']");

    public LoginPage logout() {
        dropdownTab.click();
        logout.click();
        webdriver().shouldHave(url(ConfigLoader.getInstance().getBaseUri() + LOGIN));
        return new LoginPage();
    }


}
