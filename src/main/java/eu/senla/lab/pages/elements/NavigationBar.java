package eu.senla.lab.pages.elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class NavigationBar {

    private SelenideElement jobTab = $x("//span[contains(text(), 'Job')]");

    private SelenideElement jobTitleOption = $x("//a[text()='Job Titles']");


    public SelenideElement getJobTab() {
        return jobTab;
    }

    public SelenideElement jobTitleOption() {
        return jobTitleOption;
    }

}
