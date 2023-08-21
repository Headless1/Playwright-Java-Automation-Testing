package tests;

import com.microsoft.playwright.*;

import java.io.File;
import java.nio.file.Paths;

public class BaseTest {
    public static Browser browser;
    public static Page page;
    public static BrowserContext context;

    public void setUp() {
        Playwright playwright = Playwright.create();
        browser = playwright
                .chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080)
                .setStorageStatePath(Paths.get("authentication.json")));
        page = context.newPage();
    }

    public void authorization() {
        Playwright playwright = Playwright.create();
        Browser browser1 = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser1.newContext();
        Page page1 = browserContext.newPage();
        page1.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        page1.fill("//input[@placeholder='Username']", "Admin");
        page1.fill("//input[@placeholder='Password']", "admin123");
        page1.click("[type=submit]");
        browserContext.storageState(new BrowserContext.StorageStateOptions()
                .setPath(Paths.get("authentication.json")));

    }

    public void authorizationIfNeeded(){
        File authFile = new File("authentication.json");
        if(!authFile.exists()){
            authorization();
        }
    }


    public void skipAuthorization(){
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    }

    public void tearDown() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        context.close();
        browser.close();
    }
}


