package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class InteractionWithEmployees {
    private Page page;

    public InteractionWithEmployees(Page page){
        this.page = page;
    }

    /**
     * Fills in the data in the form.
     * Заповнює дані в формі.
     */
    public void fillEmployeeDetails(String employeeName, String username, String password, String confirmPassword){
        page.getByText("Add").click();
        page.click("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[2]/i");
        page.click("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div[2]/div[3]");
        page.click("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div/div[2]/i");
        page.click("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div[2]/div[2]");
        page.fill("//div/input[@placeholder='Type for hints...']", employeeName);
        page.click("//div[@class='oxd-autocomplete-option']/span[text()='David  Morris']");
        page.fill("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input", username);
        page.fill("[type=password]", password);
        page.fill("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input", confirmPassword);
    }

    public void submitForm(){
        page.getByText("Save").click();
    }

    /**
     * Deletes an employee based on the target index.
     * Видаляє співробітника за вказаним індексом.
     *
     * @param targetIndex The index of the employee to be deleted.
     */
    public void deleteEmployee(int targetIndex) {
        List<Locator> deleteIcons = page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[6]/div/button[1]/i").all();

        for (int i = 0; i < deleteIcons.size(); i++) {
            if (i == targetIndex) {
                Locator icon = deleteIcons.get(i);
                icon.click();
                break;
            }
        }
    }

}
