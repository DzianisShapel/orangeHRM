package eu.senla.lab;


import eu.senla.lab.pages.DashboardPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

public class DashboardTest extends BaseTest {

    @Test
    public void checkDashboard(){
        List<String> elements = new DashboardPage().open().
                getDashboardElements();
        validateElements(elements);
    }
    private void validateElements(List<String> elements){
        List<String> widgetNames = List.of("Time at Work", "My Actions", "Quick Launch", "Buzz Latest Posts", "Employees on Leave Today", "Employee Distribution by Sub Unit", "Employee Distribution by Location");
        Assertions.
                assertThat(elements).
                hasSize(7).
                containsAll(widgetNames);
    }
}
