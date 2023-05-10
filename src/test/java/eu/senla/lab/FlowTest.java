package eu.senla.lab;

import eu.senla.lab.pages.AdminPage;
import eu.senla.lab.pages.LoginPage;
import org.testng.annotations.Test;

public class FlowTest {


    @Test
    public void userFlowTest() throws InterruptedException {
        AdminPage adminpage =  new LoginPage().
                openLoginPage().
                login().
                openAdminPage().
                addUser().
                fillInForm();
    }
}
