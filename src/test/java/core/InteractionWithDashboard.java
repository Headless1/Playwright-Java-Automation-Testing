package core;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class InteractionWithDashboard {

    private Page page;
    public static final String AssignLeave = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[1]/button";
    public static final String LeaveList = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[2]/button";
    public static final String TimesSheets = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[3]/button";
    public static final String ApplyLeave = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[4]/button";
    public static final String MyLeave = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[5]/button";
    public static final String MyTimesheet = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[6]/button";


    public InteractionWithDashboard(Page page){
        this.page = page;
    }


    public void SelectingItemFromQuickLaunch(String iconQuickLaunch){
        page.click(iconQuickLaunch);
    }

    public void MyActions(){
        List<Locator> allMyActions = page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div/div[2]/div/div[1]/p").all();
            for(Locator allMyAction : allMyActions){
                System.out.println(allMyAction.textContent());
            }
    }

    public void itemChart() {
        List<ElementHandle> itemDistribChart = page.querySelectorAll("#app > div.oxd-layout > div.oxd-layout-container > div.oxd-layout-context > div > div:nth-child(6) > div > div.orangehrm-dashboard-widget-body.--scroll-visible > div > ul > li > span.oxd-text.oxd-text--span");
        System.out.println(itemDistribChart);
    }
}
