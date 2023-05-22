package eu.senla.lab;

import eu.senla.lab.pages.LoginPage;
import org.testng.annotations.Test;

public class LogoutTest  extends BaseTest{


    @Test
    public void logoutFromApp() {
        new LoginPage().
                openLoginPage().
                login().
                goToUserDropdown().
                logout();
    }
}
