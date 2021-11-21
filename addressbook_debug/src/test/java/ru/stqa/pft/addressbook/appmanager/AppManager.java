package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AppManager {
    WebDriver wd;
    private SessionHelper sessionHelper;
    private NavHelper navHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private String browser;

    public AppManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        //FFOptions
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile fFoxProfile = profile.getProfile("SelForTest");
        FirefoxOptions opt = new FirefoxOptions();
        opt.addPreference("network.proxy.type", 0);
        opt.setCapability(FirefoxDriver.PROFILE, fFoxProfile);

        //IEopt
        DesiredCapabilities ieopt = DesiredCapabilities.internetExplorer();
        ieopt.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);


        if (Objects.equals(browser, BrowserType.FIREFOX)) {
            wd = new FirefoxDriver(opt);
        } else if (Objects.equals(browser, BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (Objects.equals(browser, BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }

        System.out.println(wd.getTitle());
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        navHelper = new NavHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper.Login("admin", "secret");
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

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactHelper person() {
        return contactHelper;
    }
}
