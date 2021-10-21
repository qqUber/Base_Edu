package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;

import java.util.concurrent.TimeUnit;

public class AppManager {
    WebDriver wd;
    private SessionHelper sessionHelper;
    private NavHelper navHelper;
    private GroupHelper groupHelper;

    public void initFirefox() {
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile fFoxProfile = profile.getProfile("SelForTest");
        FirefoxOptions opt = new FirefoxOptions();
        opt.setCapability(FirefoxDriver.PROFILE, fFoxProfile);

        wd = new FirefoxDriver(opt);
        System.out.println(wd.getTitle());
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.xpath("//body")).click();
        groupHelper = new GroupHelper(wd);
        navHelper = new NavHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.Login("admin", "secret");
    }

    public void stop() {
        wd.close();
    }

    private boolean isElementPresent(By by) {
          try {
              wd.findElement(by);
              return true;
          } catch (NoSuchElementException e) {
              return false;
          }
      }

    private boolean isAlertPresent() {
          try {
              wd.switchTo().alert();
              return true;
          } catch (NoAlertPresentException e) {
              return false;
          }
      }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavHelper getNavHelper() {
        return navHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
}