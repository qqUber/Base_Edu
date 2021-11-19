package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class PersonDeleteTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().Home();
        if (app.person().list().size() == 0) {
            app.person().create(new ContactData("dddaaa", "Join", "+74959990055", "createmyrules@com.tocom", null));
        }
    }
    @Test (enabled = true)
    public void testDelPersons() {
        List<ContactData> before = app.person().list();
        int index = before.size() - 1;
        app.person().delete(index);
        app.goTo().Home();
        List<ContactData> after = app.person().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
