package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonModTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().Home();
        if (app.person().all().size() == 0) {
            app.person().create(new ContactData().withFname("Dos").withLname("Create 3.11").withPhone("+79110009922").withEmail("123@yandex.com").withGroup(null));
        }
    }

    @Test(enabled = true)
    public void testModPerson() {
        Contacts before = app.person().all();
        ContactData modContact = before.iterator().next();
        app.person().modify(modContact);
        Contacts after = app.person().all();
        Assert.assertEquals(after.size(), before.size());

//        assertThat(after, equalTo(before.withModified(modContact)));
        assertThat(after, equalTo(
                before.without(modContact)
                        .withAdded(modContact)));
    }

}
