package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.fillAddnewEnum;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class AddPersonTests extends TestBase {
    private WebDriver wd;
    private boolean acceptNextAlert = true;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        wd = new FirefoxDriver();
        System.out.println(wd.getTitle());
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.xpath("//body")).click();
        Login("admin", "secret");
    }

    @Test
    public void testAddPerson() {
        gotoAddnew();
        fillAddnew(new fillAddnewEnum("Trsu", "dddaaa", "wwwqq", "nicknick", "+74959990055", "+71110099666", "createmyrules@com.tocom", "September", "2000", "i bought one mdma", "Moscow"));
        submitAddnew();
        //DeleteAllPerson();
    }

    private void fillAddnew(fillAddnewEnum fillAddnewEnum) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(fillAddnewEnum.getFname());
        wd.findElement(By.name("middlename")).click();
        wd.findElement(By.name("middlename")).clear();
        wd.findElement(By.name("middlename")).sendKeys(fillAddnewEnum.getMname());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(fillAddnewEnum.getLname());
        wd.findElement(By.name("nickname")).clear();
        wd.findElement(By.name("nickname")).sendKeys(fillAddnewEnum.getNick());
        wd.findElement(By.name("theform")).click();
        wd.findElement(By.name("home")).click();
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(fillAddnewEnum.getHphone());
        wd.findElement(By.name("mobile")).click();
        wd.findElement(By.name("mobile")).clear();
        wd.findElement(By.name("mobile")).sendKeys(fillAddnewEnum.getMphone());
        wd.findElement(By.name("email")).click();
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(fillAddnewEnum.getEmail());
        wd.findElement(By.name("bday")).click();
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText("18");
        wd.findElement(By.name("bmonth")).click();
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(fillAddnewEnum.getBmonth());
        wd.findElement(By.name("byear")).click();
        wd.findElement(By.name("byear")).clear();
        wd.findElement(By.name("byear")).sendKeys(fillAddnewEnum.getYear());
        wd.findElement(By.name("new_group")).click();
       // new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(fillAddnewEnum.getGetGroup());
        wd.findElement(By.name("phone2")).click();
        wd.findElement(By.name("phone2")).clear();
        wd.findElement(By.name("phone2")).sendKeys(fillAddnewEnum.getCity());
        wd.findElement(By.name("address2")).click();
        wd.findElement(By.name("address2")).clear();
        wd.findElement(By.name("address2")).sendKeys("fullAddress");
        wd.findElement(By.id("container")).click();
    }

        private void submitAddnew() {
        wd.findElement(By.xpath("//input[21]")).click();
        wd.findElement(By.linkText("home page")).click();
        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    private void gotoAddnew() {
        wd.findElement(By.linkText("add new")).click();
    }

    private void DeleteAllPerson() {
        acceptNextAlert = true;
        wd.findElement(By.xpath("//input[@id='MassCB']")).click();
        wd.findElement(By.xpath("//input[@value='Delete']")).click();
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        wd.quit();
    }
    private void Login(String username, String password) {
        //Login
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.id("LoginForm")).submit();
        wd.findElement(By.linkText("home")).click();
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

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = wd.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}