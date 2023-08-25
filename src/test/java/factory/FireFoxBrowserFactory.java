package factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import factory.BrowserFactory;

public class FireFoxBrowserFactory implements BrowserFactory {
    @Override
    public Browser createBrowser() {
        Playwright playwright = Playwright.create();
        return playwright
                .firefox()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));
    }
}
