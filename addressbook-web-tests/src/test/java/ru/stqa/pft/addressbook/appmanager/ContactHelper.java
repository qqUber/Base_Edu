package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillAddnew(ContactData ContactData, boolean creation) {
        type(By.name("firstname"), ContactData.getFname());
        type(By.name("middlename"), ContactData.getMname());
        type(By.name("lastname"), ContactData.getLname());
        type(By.name("nickname"), ContactData.getNick());
        //click(By.name("theform"));
        type(By.name("home"), ContactData.getPhone());
        type(By.name("mobile"), ContactData.getMphone());
        type(By.name("email"), ContactData.getEmail());
        click(By.name("bday"));
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText("18");
        wd.findElement(By.name("bmonth")).click();
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(ContactData.getBmonth());
        type(By.name("byear"), ContactData.getYear());
        //click(By.name("new_group"));
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(ContactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        type(By.name("phone2"), ContactData.getCity());
        type(By.name("address2"), "fullAddress");

        //wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public void returnHome() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void selectAllPerson() {
        wd.findElement(By.xpath("//input[@id='MassCB']")).click();
    }

    public void deletePersons() {
        wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    public void submitAdd() {
        click(By.xpath("//input[@name='submit']"));
    }

    public void selectPerson() {
        click(By.name("selected[]"));
    }

    public void updatePerson() {
        click(By.xpath("//input[@name='update']"));
    }

    public void editPerson() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void createPerson(ContactData contactData, boolean b) {
        gotoAddnew();
        fillAddnew(contactData, b);
        submitAdd();
        returnHome();
    }
    public void gotoAddnew() {
        click(By.xpath("//a[contains(text(),'add new')]"));
    }
    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}