package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
            if (ContactData.getGroup() != null) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(ContactData.getGroup());
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

    public void selectPerson(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    private void selectPersonById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void updatePerson() {
        click(By.xpath("//input[@name='update']"));
    }

    public void editPerson() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void editPersonById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void create(ContactData contact) {
        gotoAddnew();
        fillAddnew(contact, true);
        submitAdd();
        returnHome();
    }

    public void modify(ContactData contact) {
        editPersonById(contact.getId());
        fillAddnew(contact, false);
        updatePerson();
        returnHome();
    }

    public void delete(ContactData contact) {
        selectPersonById(contact.getId());
        deletePersons();
        acceptAlert();
    }

    public void gotoAddnew() {
        click(By.xpath("//a[contains(text(),'add new')]"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("table tr[name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) input")).getAttribute("id"));
            String lname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String fname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            contacts.add(new ContactData().withId(id).withFname(fname).withLname(lname));
        }
        return contacts;
    }
}