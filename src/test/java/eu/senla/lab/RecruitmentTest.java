package eu.senla.lab;


import com.github.javafaker.Faker;
import eu.senla.lab.objects.Employee;
import eu.senla.lab.pages.LoginPage;
import org.testng.annotations.Test;

public class RecruitmentTest {


    @Test
    public void addCandidate() {
        Employee employee = new Employee(new Faker().name().firstName(), new Faker().name().lastName(), null);
        employee.setEmail(new Faker().internet().emailAddress());
        new LoginPage().
                openLoginPage().
                login().
                openRecruitmentPage().
                addCandidate(employee).
                checkCandidateProfile(employee);
    }
}
