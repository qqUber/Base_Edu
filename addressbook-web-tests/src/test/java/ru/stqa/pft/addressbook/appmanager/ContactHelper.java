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

    public void fillAddnew(ContactData ContactData, boolean creation) {
        type(By.name("firstname"), ContactData.getFname());
        type(By.name("lastname"), ContactData.getLname());
        type(By.name("home"), ContactData.getPhone());
        type(By.name("email"), ContactData.getEmail());
        //click(By.name("new_group"));
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(ContactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
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

    public void selectPerson(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void updatePerson() {
        click(By.xpath("//input[@name='update']"));
    }

    public void editPerson() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void createPerson(ContactData contactData) {
        gotoAddnew();
        fillAddnew(contactData, true);
        submitAdd();
        returnHome();
    }

    public void gotoAddnew() {
        click(By.xpath("//a[contains(text(),'add new')]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.xpath("./td/input")).getAttribute("value"));
            ContactData contact = new ContactData(id, name, "Mustroi",null, null, "3");
            contacts.add(contact);
        }
        return contacts;
    }
}