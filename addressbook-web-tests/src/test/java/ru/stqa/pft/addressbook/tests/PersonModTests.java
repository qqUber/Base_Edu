package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class PersonModTests extends TestBase {
    @Test (enabled = false)
    public void testModPerson() {
        app.getNavHelper().gotoHome();
        if (!app.getNavHelper().isThereAGroup()) {
            app.getPersonHelper().createPerson(new ContactData("dddaaa", "Join", "+74959990055", "createmyrules@com.tocom", null));
        }
        List<ContactData> before = app.getPersonHelper().getContactList();
        app.getPersonHelper().selectPerson(before.size() - 1);
        app.getPersonHelper().editPerson();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Join", "Mustroi", "+71110099666", "createmyrules@com.tocom", "3");
        app.getPersonHelper().fillAddnew(contact, false);
        app.getPersonHelper().updatePerson();
        app.getPersonHelper().returnHome();
        List<ContactData> after = app.getPersonHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
