package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class PersonAddTests extends TestBase {

    @Test
    public void testAddPerson() {
        app.getNavHelper().gotoHome();
        int before = app.getPersonHelper().getContactCount();
        app.getPersonHelper().createPerson(new ContactData("Trsu", "dddaaa", "wwwqq", "nicknick", "+74959990055", "+71110099666", "createmyrules@com.tocom", "September", "2000", "Moscow", "3"), true);
        int after = app.getPersonHelper().getContactCount();
        Assert.assertEquals(after, before + 1);
    }
}
