package tests;

import core.BaseTest;
import factory.FireFoxBrowserFactory;
import core.InteractionWithEmployees;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminUserManagementTest extends BaseTest {

    @BeforeEach
    public void setUpTest(){
        // Check for authorization and set up the environment before each test
        authorizationIfNeeded();
        setBrowserFactory(new FireFoxBrowserFactory());
        setUp();
        skipAuthorization("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    }
    @AfterEach
    public void tearDownTest(){
        // Tear down the environment after each test
        tearDown();
    }


    @Test
    public void SearchEmployeePagesByUsername(){
        // Search for employees by username
       String Username = page.innerText("//div[@class='oxd-table-card'][2]//div[@class='oxd-table-cell oxd-padding-cell'][2]");
       page.fill("//div//input[@class='oxd-input oxd-input--active'][not(contains(@placeholder, 'Search'))]", Username);
       page.querySelector("//button[@type=\"submit\"]").click();
        Assertions.assertTrue(page.locator("//div[@class='oxd-table-body']//div[@class='oxd-table-cell oxd-padding-cell'][2]")
                .textContent().contains(Username));
    }

    @Test
    public void SearchEmployeePagesByEmployeeName() {
        // Search for employees by name
        String EmployeeName = page.innerText("//div[@class='oxd-table-card'][3]//div[@class='oxd-table-cell oxd-padding-cell'][4]");
        page.fill("//div/input[@placeholder='Type for hints...']", EmployeeName);
        page.click("//div[@class='oxd-autocomplete-option']/span");
        page.querySelector("//button[@type=\"submit\"]").click();
        Assertions.assertTrue(page.locator("//div[@class='oxd-table-body']//div[@class='oxd-table-cell oxd-padding-cell'][4]")
                .textContent().contains(EmployeeName));
    }

    @Test
    public void SearchEmployeePagesByUserRoleAndStatus(){
        page.click("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[2]/i");
        page.click("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div[2]/div[3]/span");
        page.click("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[2]/i");
        page.click("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div[2]/div[2]/span");
        page.querySelector("//button[@type=\"submit\"]").click();
        Assertions.assertTrue(page.locator("//div[@class=\"oxd-table-card\"][1]//*[text()='ESS']")
                .textContent().contains("ESS"));
        Assertions.assertTrue(page.locator("//div[@class=\"oxd-table-card\"][1]//*[text()='Enabled']")
                .textContent().contains("Enabled"));
    }

    @Test
    public void AddNewUser(){
        // Adding a new user through InteractionWithEmployees class
        InteractionWithEmployees interactionWithEmployees= new InteractionWithEmployees(page);
        interactionWithEmployees.fillEmployeeDetails("John William", "Nilson.Lesli", "l1234567", "l1234567");
        interactionWithEmployees.submitForm();
        Assertions.assertTrue(page.isVisible("//h5"));
    }

    @Test
    public void deleteEmployee(){
        // Deleting employees with incorrect usernames
        page.click("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[3]/div/div[6]/div/button[1]/i");
        Assertions.assertTrue(page.isVisible("//*[@id=\"app\"]/div[3]/div/div/div/div[1]/p"));
    }


}

