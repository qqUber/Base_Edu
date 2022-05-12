package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AppManager {
    private final Properties properties;
    private final String browser;
    WebDriver wd;
    private SessionHelper sessionHelper;
    private NavHelper navHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private DbHelper dbHelper;

    public AppManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        dbHelper = new DbHelper();

        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));

        if ("".equals(properties.getProperty("selenium.server"))) {
                if (BrowserType.FIREFOX.equals(browser)) {
                    wd = new FirefoxDriver();
                } else if (BrowserType.CHROME.equals(browser)) {
                    wd = new ChromeDriver();
                } else if (BrowserType.IE.equals(browser)) {
                    wd = new InternetExplorerDriver();
                } else if (BrowserType.EDGE.equals(browser)) {
                    wd = new EdgeDriver();
                }
            } else {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(browser);
                //capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win10")));
                wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }

        System.out.println(wd.getTitle());
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(wd);
        navHelper = new NavHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper.Login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        wd.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavHelper goTo() {
        return navHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }
}
