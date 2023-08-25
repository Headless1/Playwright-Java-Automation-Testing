package core;

import com.microsoft.playwright.*;
import factory.BrowserFactory;

import java.io.File;
import java.nio.file.Paths;

abstract public class BaseTest {
    public static Browser browser;
    public static Page page;
    public static BrowserContext context;
    private static BrowserFactory browserFactory;

    public static void setBrowserFactory(BrowserFactory factory){
        browserFactory = factory;
    }

    /**
     * Sets up the basic environment for the test.
     * Initializes the browser, page, and context.
     * Встановлює базове середовище для тесту.
     * Ініціалізує браузер, сторінку та контекст.
     */
    public void setUp() {
        browser = browserFactory.createBrowser();
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080)
                .setStorageStatePath(Paths.get("authentication.json")));
        page = context.newPage();
    }

    /**
     * Executes the authorization process on the page.
     * Uses a separate browser and context for state storage.
     * Виконує процедуру авторизації на сторінці.
     * Використовує окремий браузер та контекст для збереження стану.
     */
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

    /**
     * Executes authorization if the "authentication.json" file is absent.
     * Виконує авторизацію, якщо файл "authentication.json" відсутній.
     */
    public void authorizationIfNeeded(){
        File authFile = new File("authentication.json");
        if(!authFile.exists()){
            authorization();
        }
    }


    public void skipAuthorization(String url){
        page.navigate(url);
    }

    /**
     * Completes the test workflow.
     * Closes the context and browser.
     * Завершує роботу тесту.
     * Закриває контекст та браузер.
     */
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


