package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class PersonDeleteTests extends TestBase {
    @Test (enabled = false)
    public void testDelPersons() {
        app.getNavHelper().gotoHome();
        if (!app.getNavHelper().isThereAGroup()) {
            app.getPersonHelper().createPerson(new ContactData("Trsu", "wwwqq", "+71110099666", "createmyrules@com.tocom", null));
        }
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
