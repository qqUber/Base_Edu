package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class PersonModTests extends TestBase {
    @Test
    public void testModPerson() {
        app.getNavHelper().gotoHome();
        if (!app.getNavHelper().isThereAGroup()) {
            app.getPersonHelper().createPerson(new ContactData("Trsu", "dddaaa", "wwwqq", "nicknick", "+74959990055", "+71110099666", "createmyrules@com.tocom", "September", "2000", "Moscow", "3"), true);
        }
        List<ContactData> before = app.getPersonHelper().getContactList();
        app.getPersonHelper().selectPerson(before.size() - 1);
        app.getPersonHelper().editPerson();
        app.getPersonHelper().fillAddnew(new ContactData("Common", "Join", "Mustroi", "nicknick", "+74959990055", "+71110099666", "createmyrules@com.tocom", "September", "2000", "Moscow", null), false);
        app.getPersonHelper().updatePerson();
        app.getPersonHelper().returnHome();
        List<ContactData> after = app.getPersonHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }
}
