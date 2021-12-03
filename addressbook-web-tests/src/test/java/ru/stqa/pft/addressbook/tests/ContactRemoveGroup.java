package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
public class ContactRemoveGroup extends TestBase {

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

    @Test
    public void testContactRemoveFromGroup() {
        GroupData selectedGroup = selectGroup();
        ContactData selectedContact = selectContact(selectedGroup);
        Groups contactBefore = selectedContact.getGroups();
        app.goTo().homePage();
        app.contact().removeFromGroup(selectedContact, selectedGroup);
        Groups contactAfter = app.db().contactById(selectedContact.getId()).getGroups();
        assertThat(contactAfter, equalTo(contactBefore.without(app.db().groupById(selectedGroup.getId()))));
        verifyContactListInUI();
    }

    private GroupData selectGroup() {
        Groups groups = app.db().groups();
        for (GroupData group : groups) {
            if (group.getContacts().size() > 0) {
                return group;
            }
        }
        return groups.iterator().next();
    }

    private ContactData selectContact(GroupData selectedGroup) {
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if (contact.getGroups().contains(selectedGroup)) {
                return contact;
            }
        }
        ContactData selectedContact = contacts.iterator().next();
        app.contact().addIntoGroup(selectedContact, selectedGroup);
        return selectedContact;
    }
}