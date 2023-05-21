package eu.senla.lab;

import com.github.javafaker.Faker;
import eu.senla.lab.api.ApiHelper;
import eu.senla.lab.objects.JobTitle;
import eu.senla.lab.pages.AdminPage;
import eu.senla.lab.pages.LoginPage;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class JobTitleTest {

    @Test
    public void addJobTitle() {
        List<String> titles = List.of(new Faker().company().profession(), new Faker().company().profession(), new Faker().company().profession());
        AdminPage adminPage = new LoginPage().
                openLoginPage().
                login().
                openAdminPage().
                goToNavigationBar().
                navigateToJobTitles();
        titles.forEach(adminPage::addJobTitle);
        titles.forEach(adminPage::checkRecordInTable);
    }

    @Test
    public void deleteJobTitles(){
        List<JobTitle> jobTitles = new ArrayList<>();
        jobTitles.add(new JobTitle("title to delete", new Faker().company().profession()));
        jobTitles.add(new JobTitle("title to delete", new Faker().company().profession()));
        jobTitles.add(new JobTitle("title to delete", new Faker().company().profession()));
        jobTitles.forEach(ApiHelper::addJobTitle);
        AdminPage adminPage = new LoginPage().
                openLoginPage().
                login().
                openAdminPage().
                goToNavigationBar().
                navigateToJobTitles();
        jobTitles.forEach(adminPage::deleteJobTitle);
    }
}
