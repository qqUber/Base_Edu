package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().Home();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFname("Dos").withLname("Create 3.11").withHphone("+79110009922").withEmail("123@yandex.com"));
        }
    }

    @Test(enabled = true)
    public void testModPerson() {
        Contacts before = app.db().contacts();
        ContactData modContact = before.iterator().next();
        app.contact().modify(modContact);
        Contacts after = app.db().contacts();

        assertThat(after.size(), equalTo(
                before.size()));
        assertThat(after, equalTo(
                before.without(modContact)
                        .withAdded(modContact)));
        verifyContactListInUI();
    }
}
