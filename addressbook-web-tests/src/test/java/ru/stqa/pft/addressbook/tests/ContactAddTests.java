package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddTests extends TestBase {

    @Test
    public void testAddPerson() {
        app.goTo().Home();
        Contacts before = app.person().all();
        //File photo = new File("src/test/resources/data.png");
        ContactData contact = new ContactData().withFname("Dos").withLname("Create 3.11").withHphone("+79110009922").withEmail("123@yandex.com");
        app.person().create(contact);
        Contacts after = app.person().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream()
                .mapToInt(ContactData::getId)
                .max()
                .orElseGet(null)))));
    }
}
