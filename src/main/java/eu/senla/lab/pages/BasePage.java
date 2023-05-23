package eu.senla.lab.pages;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import eu.senla.lab.api.actions.AuthHelper;
import eu.senla.lab.pages.elements.UserDropdown;
import eu.senla.lab.utils.CookieUtils;
import io.restassured.http.Cookie;

import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    private final UserDropdown userDropdown = new UserDropdown();
    public SelenideElement header = $(".oxd-topbar-header-breadcrumb-module");

    public SelenideElement addButton = $(".oxd-button-icon");

    public SelenideElement submitButton = $("[type='submit']");

    public SelenideElement spinner = $x("//div[@class='oxd-loading-spinner']");


    public UserDropdown goToUserDropdown(){
            return userDropdown;
    }

    public void injectCookieToBrowser(){
        Cookie cookie = AuthHelper.getAuthCookie();
        org.openqa.selenium.Cookie selCookie = CookieUtils.convertRestAssuredToSeleniumCookie(cookie);
        WebDriverRunner.getWebDriver().manage().addCookie(selCookie);
        Selenide.refresh();
    }


}
