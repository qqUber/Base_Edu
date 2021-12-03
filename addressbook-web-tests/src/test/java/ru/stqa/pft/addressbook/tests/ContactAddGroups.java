package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddGroups extends TestBase {

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
    public void testContactAddIntoGroup() {
        GroupData selectedGroup = selectGroup();
        ContactData selectedContact = selectContact(selectedGroup);
        Groups contactBefore = selectedContact.getGroups();
        app.goTo().homePage();
        app.contact().addIntoGroup(selectedContact, selectedGroup);
        Groups contactAfter = app.db().contactById(selectedContact.getId()).getGroups();
        assertThat(contactAfter, equalTo(contactBefore.withAdded(app.db().groupById(selectedGroup.getId()))));
        verifyContactListInUI();
    }

    private ContactData selectContact(GroupData selectedGroup) {
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if (!contact.getGroups().contains(selectedGroup)) {
                return contact;
            }
        }
        app.goTo().homePage();
        app.contact().create(new ContactData().withFname("Oppsol").withLname("Loppi").withMphone("+79161112200")
                .withEmail("pop@mail.ru"));
        Contacts contactsWithAdded = app.db().contacts();
        return app.db().contactById(contactsWithAdded.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    }

    private GroupData selectGroup() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        for (GroupData group : groups) {
            if (group.getContacts().size() < contacts.size()) {
                return group;
            }
        }
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("Source"));
        Groups groupsWithAdded = app.db().groups();
        return app.db().groupById(groupsWithAdded.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    }
}