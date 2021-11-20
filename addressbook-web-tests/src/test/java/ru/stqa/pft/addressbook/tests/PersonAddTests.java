package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class PersonAddTests extends TestBase {

    @Test (enabled = true)
    public void testAddPerson() {
        app.goTo().Home();
        List<ContactData> before = app.person().list();
        ContactData contact = new ContactData("Join", "Mustroi", null, null, null);
        app.person().create(contact);
        List<ContactData> after = app.person().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byLastName = Comparator.comparing(ContactData::getLname);
        before.sort(byLastName);
        after.sort(byLastName);
        Assert.assertEquals(before, after);
    }
}
