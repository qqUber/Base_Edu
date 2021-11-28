package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends BaseHelper {
    private Contacts contactCache = null;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillAddnew(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFname());
        type(By.name("lastname"), contactData.getLname());
        type(By.name("home"), contactData.getHphone());
        type(By.name("email"), contactData.getEmail());
        //attach(By.name("photo"), contactData.getPhoto());
        //click(By.name("new_group"));
        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertEquals(contactData.getGroups().size(), 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }
        public void returnHome () {
            if (isElementPresent(By.xpath("//a[contains(@href, 'index.php')]"))) {
                wd.findElement(By.linkText("home page")).click();
            }
            click(By.xpath("//a[contains(text(),'home')]"));
        }

        public void selectAllPerson () {
            wd.findElement(By.xpath("//input[@id='MassCB']")).click();
        }

        public void deletePersons () {
            wd.findElement(By.xpath("//input[@value='Delete']")).click();
        }

        public void acceptAlert () {
            wd.switchTo().alert().accept();
        }

        public void submitCreate () {
            click(By.xpath("//input[@name='submit']"));
        }

        public void submitAdd () {
            click(By.xpath("//input[@name='add']"));
        }

    private void submitAddGroup() {
        wd.findElement(By.name("add")).click();
    }
        private void selectPersonById ( int id){
            wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
        }

        public void updatePerson () {
            click(By.xpath("//input[@name='update']"));
        }

        public void editPersonById ( int id){
            wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
        }

        public void create (ContactData contact){
            gotoAddnew();
            fillAddnew(contact, true);
            submitCreate();
            contactCache = null;
            returnHome();
        }

        public void modify (ContactData contact){
            editPersonById(contact.getId());
            fillAddnew(contact, false);
            updatePerson();
            contactCache = null;
            returnHome();
        }

        public void delete (ContactData contact){
            selectPersonById(contact.getId());
            deletePersons();
            contactCache = null;
            acceptAlert();
        }

        public void gotoAddnew () {
            click(By.xpath("//a[contains(text(),'add new')]"));
        }

        public Contacts all () {
            if (contactCache != null) {
                return new Contacts(contactCache);
            }
            contactCache = new Contacts();
            List<WebElement> rows = wd.findElements(By.name("entry"));
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
                String lname = cells.get(1).getText();
                String fname = cells.get(2).getText();
                String address = cells.get(3).getText();
                String emails = cells.get(4).getText();
                String phones = cells.get(5).getText();
                contactCache.add(new ContactData()
                        .withId(id)
                        .withFname(fname)
                        .withLname(lname)
                        .withAllPhones(phones)
                        .withAllEmails(emails)
                        .withAddress(address));
            }
            return new Contacts(contactCache);
        }

        public ContactData infoFromEditForm (ContactData contact){
            editPersonById(contact.getId());
            String lname = wd.findElement(By.name("firstname")).getAttribute("value");
            String fname = wd.findElement(By.name("lastname")).getAttribute("value");
            String hphone = wd.findElement(By.name("home")).getAttribute("value");
            String mphone = wd.findElement(By.name("mobile")).getAttribute("value");
            String wphone = wd.findElement(By.name("work")).getAttribute("value");
            String address = wd.findElement(By.name("address")).getText();
            String email = wd.findElement(By.name("email")).getAttribute("value");
            String email2 = wd.findElement(By.name("email2")).getAttribute("value");
            String email3 = wd.findElement(By.name("email3")).getAttribute("value");
            wd.navigate().back();
            return new ContactData()
                    .withId(contact.getId())
                    .withFname(fname)
                    .withLname(lname)
                    .withHphone(hphone)
                    .withMphone(mphone)
                    .withWphone(wphone)
                    .withAddress(address)
                    .withEmail(email)
                    .withEmail2(email2)
                    .withEmail3(email3);
        }

        public void createWithGroup (ContactData before, GroupData group4Add){
            selectPersonById(before.getId());
            selGroupForContact(group4Add.getName());
            submitAddGroup();
            returnToGroup(group4Add);
        }

        private void returnToGroup (GroupData group){
            wd.findElement(By.linkText("group page \"" + group.getName() + "\""));
        }

        private void selGroupForContact (String name){
            new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(name);
        }
    }