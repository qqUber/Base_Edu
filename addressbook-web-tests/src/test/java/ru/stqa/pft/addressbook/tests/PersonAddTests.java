package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class PersonAddTests extends TestBase {

    @Test
    public void testAddPerson() {
        app.getNavHelper().gotoHome();
        List<ContactData> before = app.getPersonHelper().getContactList();
        ContactData contact = new ContactData("Dddaaa", "Mustroi", null, null, "3");
        app.getPersonHelper().createPerson(contact);
        List<ContactData> after = app.getPersonHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        int max = 0;
        for (ContactData g : after) {
            if (g.getId() > max) {
                max = g.getId();
            }
        }
        contact.setId(max);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
