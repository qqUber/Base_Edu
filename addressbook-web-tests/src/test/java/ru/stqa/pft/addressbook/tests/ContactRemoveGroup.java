package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
public class ContactRemoveGroup extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Plus1").withHeader("pop").withFooter("top"));
            app.goTo().homePage();
        }

        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFname("Oppsol").withLname("Loppi").withMphone("+79161112200")
                    .withEmail("pop@mail.ru").addGroup(app.db().groups().iterator().next()));
        }
    }

    public ContactData checkContactWithGroup(ContactData contact) {

        if (contact.getGroups().size() == 0) {
            app.contact().createWithGroup(contact, app.db().groups().iterator().next());
            app.goTo().homePage();
        }
        return contact;
    }

    @Test
    public void testRemoveBindGroups() {
        ContactData contact = app.db().contacts().iterator().next();
        checkContactWithGroup(contact);
        ContactData before = app.db().contacts().iterator().next();
        GroupData removeGroup = before.getGroups().iterator().next();
        app.contact().removeFromGroup(contact, removeGroup);
        ContactData after = app.db().contacts().iterator().next();

        assertThat(before.getGroups(), equalTo(after.addGroup(removeGroup).getGroups()));
    }
}