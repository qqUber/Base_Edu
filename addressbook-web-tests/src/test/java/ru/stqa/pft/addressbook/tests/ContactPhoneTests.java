package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().Home();
        if (app.person().all().size() == 0) {
            app.person().create(new ContactData().withFname("Dos").withLname("Create 3.11").withHphone("+79110009922").withEmail("123@yandex.com").withGroup(null));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().Home();
        ContactData contact = app.person().all().iterator().next();
        ContactData contactInfoFromEditForm = app.person().infoFromEditForm(contact);

        assertThat(contact.getAllphones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHphone(), contact.getMphone(), contact.getWphone()).filter((s) -> !s.equals(""))
          .map(ContactPhoneTests::cleaned)
          .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
