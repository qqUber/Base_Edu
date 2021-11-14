package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class PersonAddTests extends TestBase {

    @Test
    public void testAddPerson() {
        app.getNavHelper().gotoHome();
        List<ContactData> before = app.getPersonHelper().getContactList();
        app.getPersonHelper().createPerson(new ContactData("Trsu", "dddaaa", "wwwqq", "nicknick", "+74959990055", "+71110099666", "createmyrules@com.tocom", "September", "2000", "Moscow", "3"), true);
        List<ContactData> after = app.getPersonHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}
