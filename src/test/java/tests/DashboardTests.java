package tests;

import com.microsoft.playwright.Locator;
import core.BaseTest;
import factory.FireFoxBrowserFactory;
import core.InteractionWithDashboard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DashboardTests extends BaseTest {

    @BeforeEach
    public void setUpTest(){
        // Check for authorization and set up the environment before each test
        authorizationIfNeeded();
        setBrowserFactory(new FireFoxBrowserFactory());
        setUp();
        skipAuthorization("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }
    @AfterEach
    public void tearDownTest(){
        // Tear down the environment after each test
        tearDown();
    }

    @Test
    public void QuickLaunchTest(){
        InteractionWithDashboard interaction = new InteractionWithDashboard(page);
        interaction.SelectingItemFromQuickLaunch(InteractionWithDashboard.AssignLeave);
        Assertions.assertTrue(page.locator("//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title']")
                .textContent().contains("Assign Leave"));
    }

    @Test
    public void MyActionsTests(){
        InteractionWithDashboard interaction = new InteractionWithDashboard(page);
        interaction.MyActions();
    }

    @Test
    public void BuzzLatestPosts(){
        page.locator("//div[@class='orangehrm-buzz-widget-header']").first().click();
        Assertions.assertTrue(page.locator("//p[@class='oxd-text oxd-text--p oxd-text--card-title orangehrm-buzz-newsfeed-title']")
                .textContent().contains("Buzz Newsfeed"));

    }

    @Test
    public void EmployeesOnLeaveToday(){
     Locator elemet = page.locator("//div[@class='orangehrm-leave-card']");

        if(elemet.isVisible()){
            System.out.println("Element is present on the page.");
            Assertions.assertTrue(page.isVisible("//div[@class='orangehrm-leave-card'][1]"));
        }else {
            System.out.println("No records found.");
        }
    }

}
