package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupTestCase {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() {
    System.setProperty("webdriver.gecko.driver", "Your gecko driver path");
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testGroupAdd() {
    wd.get("http://localhost/addressbook/");
    wd.findElement(By.id("LoginForm")).click();
    wd.findElement(By.xpath("//body")).click();
    wd.findElement(By.xpath("//input[@value='Login']")).click();
    wd.findElement(By.linkText("add new")).click();
    wd.findElement(By.linkText("home")).click();
    wd.findElement(By.linkText("groups")).click();
    wd.findElement(By.name("new")).click();
    wd.findElement(By.xpath("//div[@id='content']/form/label")).click();
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).clear();
    wd.findElement(By.name("group_name")).sendKeys("test_group_name");
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).clear();
    wd.findElement(By.name("group_header")).sendKeys("test.headerGroup");
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).clear();
    wd.findElement(By.name("group_name")).sendKeys("test.groupName");
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).clear();
    wd.findElement(By.name("group_footer")).sendKeys("test.groupFooter");
    wd.findElement(By.name("submit")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
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

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
