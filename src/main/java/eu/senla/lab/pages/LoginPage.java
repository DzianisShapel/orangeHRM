package eu.senla.lab.pages;

import com.codeborne.selenide.SelenideElement;
import eu.senla.lab.utils.ConfigLoader;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static eu.senla.lab.constants.Route.LOGIN;

public class LoginPage {

    SelenideElement username = $x("//input[@name='username']");

    //(//input[@class='oxd-input oxd-input--active'])[1]']
    SelenideElement password = $x("//input[@name='password']");
    SelenideElement login = $("[type='submit']");
    SelenideElement title = $(".orangehrm-login-title");


    public LoginPage openLoginPage(){

        open( ConfigLoader.getInstance().getBaseUri() + LOGIN);
        title.shouldHave(text("Login"));
        return this;
    }

    public DashboardPage login(){
        username.setValue(ConfigLoader.getInstance().getUsername());
        password.setValue(ConfigLoader.getInstance().getPassword());
        login.click();
        return new DashboardPage();
    }


}
