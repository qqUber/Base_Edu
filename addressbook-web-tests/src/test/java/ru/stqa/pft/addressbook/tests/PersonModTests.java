package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class PersonModTests extends TestBase {
    @Test
    public void testModPerson() {
        app.getNavHelper().gotoHome();
        if (! app.getNavHelper().isThereAGroup()) {
            app.getPersonHelper().createPerson(new ContactData("Trsu", "dddaaa", "wwwqq", "nicknick", "+74959990055", "+71110099666", "createmyrules@com.tocom", "September", "2000", "Moscow", "3"), true);
        }
        int before = app.getPersonHelper().getContactCount();
        app.getPersonHelper().selectPerson();
        app.getPersonHelper().editPerson();
        app.getPersonHelper().fillAddnew(new ContactData("Common", "Join", "Mustroi", "nicknick", "+74959990055", "+71110099666", "createmyrules@com.tocom", "September", "2000", "Moscow", null), false);
        app.getPersonHelper().updatePerson();
        app.getPersonHelper().returnHome();
        int after = app.getPersonHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
