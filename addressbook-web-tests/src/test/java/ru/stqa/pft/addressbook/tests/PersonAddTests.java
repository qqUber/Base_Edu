package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class PersonAddTests extends TestBase {

    @Test(enabled = true)
    public void testAddPerson() {
        app.goTo().Home();
        Contacts before = app.person().all();
        ContactData contact = new ContactData().withFname("Dos").withLname("Create 3.11");
        app.person().create(contact);
        Contacts after = app.person().all();
        assertEquals(after.size(), before.size() + 1);

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream()
                .mapToInt(ContactData::getId)
                .max()
                .orElseGet(null)))));
    }
}
