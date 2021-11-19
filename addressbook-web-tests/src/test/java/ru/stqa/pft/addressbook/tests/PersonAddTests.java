package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
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

        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
