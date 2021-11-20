package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class PersonModTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().Home();
        if (app.person().list().size() == 0) {
            app.person().create(new ContactData("Dade", "Join", "+74959990055", "createmyrules@com.tocom", null));
        }
    }
    @Test (enabled = true)
    public void testModPerson() {
        List<ContactData> before = app.person().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData("Join", "Mustroi", null, null, null);
        app.person().modify(contact);
        List<ContactData> after = app.person().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byLastName = Comparator.comparing(ContactData::getLname);
        before.sort(byLastName);
        after.sort(byLastName);
        Assert.assertEquals(before, after);
    }

}
