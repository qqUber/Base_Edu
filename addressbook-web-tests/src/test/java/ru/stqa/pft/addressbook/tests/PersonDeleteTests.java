package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class PersonDeleteTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavHelper().gotoHome();
        if (!app.getNavHelper().isThereAGroup()) {
            app.getPersonHelper().createPerson(new ContactData("dddaaa", "Join", "+74959990055", "createmyrules@com.tocom", null));
        }
    }
    @Test (enabled = false)
    public void testDelPersons() {
        List<ContactData> before = app.getPersonHelper().getContactList();
        app.getPersonHelper().selectPerson(before.size() - 1);
        app.getPersonHelper().deletePersons();
        app.getPersonHelper().acceptAlert();
        app.getNavHelper().gotoHome();
        List<ContactData> after = app.getPersonHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
