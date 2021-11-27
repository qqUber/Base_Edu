package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().Home();
        if (app.person().all().size() == 0) {
            app.person().create(new ContactData().withFname("Dos").withLname("Create 3.11").withHphone("+79110009922").withEmail("123@yandex.com").withGroup(null));
        }
    }

    @Test
    public void testDelPersons() {
        Contacts before = app.person().all();
        ContactData delContact = before.iterator().next();
        app.person().delete(delContact);
        app.goTo().Home();
        Contacts after = app.person().all();
        assertThat(after.size(), equalTo(before.size() - 1));

        assertThat(after, equalTo(before.without(delContact)));

    }

}