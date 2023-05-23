package eu.senla.lab.pages;


import com.codeborne.selenide.SelenideElement;
import eu.senla.lab.pages.elements.UserDropdown;

import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    private UserDropdown userDropdown = new UserDropdown();
    public SelenideElement header = $(".oxd-topbar-header-breadcrumb-module");

    public SelenideElement addButton = $(".oxd-button-icon");

    public SelenideElement submitButton = $("[type='submit']");

    public SelenideElement spinner = $x("//div[@class='oxd-loading-spinner']");


    public UserDropdown goToUserDropdown(){
            return userDropdown;
    }


}
