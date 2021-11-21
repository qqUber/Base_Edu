package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillAddnew(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFname());
        type(By.name("lastname"), contactData.getLname());
        type(By.name("home"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());
        //click(By.name("new_group"));
        if (creation) {
            if (contactData.getGroup() != null) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            } else {
                Assert.assertTrue(isElementPresent(By.name("new_group")));
            }
            type(By.name("address2"), "fullAddress");
        }
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

    public void updatePerson() {
        click(By.xpath("//input[@name='update']"));
    }


    public void selectPerson(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void editPerson(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void create(ContactData contact) {
        gotoAddnew();
        fillAddnew(contact, true);
        submitAdd();
        returnHome();
    }

    private void gotoAddnew() {
        click(By.xpath("//a[contains(text(),'add new')]"));
    }

    public void modify(int index, ContactData contact) {
        editPerson(index);
        fillAddnew(contact, false);
        updatePerson();
        returnHome();
    }

    public void delete(int index) {
        selectPerson(index);
        deletePersons();
        acceptAlert();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("table tr[name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.xpath("./td/input")).getAttribute("value"));
            String lname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String fname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            ContactData contact = new ContactData(id, fname, lname, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}