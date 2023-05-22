package eu.senla.lab;

import eu.senla.lab.objects.Employee;
import eu.senla.lab.pages.LoginPage;
import org.testng.annotations.Test;

public class PIMTest extends BaseTest{

    @Test
    public void searchByEmployeeName(){
        Employee employee = createEmployee();
        new LoginPage().
                openLoginPage().
                login().
                openPIMPage().
                getEmployeeInformationForm().
                setEmployeeName(employee).
                clickSubmitButton().
                checkRecordInTable(employee.getFirstName());
    }

    @Test
    public void searchByEmployeeId(){
        Employee employee = createEmployee();
        new LoginPage().
                openLoginPage().
                login().
                openPIMPage().
                getEmployeeInformationForm().
                setEmployeeId(employee.getEmployeeId()).
                clickSubmitButton().
                checkRecordInTable(employee.getEmployeeId());
    }

}
