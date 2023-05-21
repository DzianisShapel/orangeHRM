package eu.senla.lab.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    public SelenideElement header = $(".oxd-topbar-header-breadcrumb-module");

    public SelenideElement addButton = $(".oxd-button-icon");

    public SelenideElement submitButton = $("[type='submit']");

    public SelenideElement spinner = $x("//div[@class='oxd-loading-spinner']");

    public SelenideElement selectDropdown = $x("//div[@class='oxd-select-dropdown --positon-bottom']");
    public ElementsCollection selectOptions = $$x("//div[@class='oxd-select-dropdown --positon-bottom']//descendant::span");
    public SelenideElement employeeName = $x("//input[contains(@placeholder,'Type for hints')]");

    public SelenideElement modalConfirmButton = $(".oxd-button--secondary.orangehrm-button-margin");

}
