package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddGroups extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFname("Dos")
                    .withLname("Create 3.11")
                    .withHphone("+79110009922")
                    .withEmail("123@yandex.com"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("213SS"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddPersonWithGroups() {
      ContactData before = app.db().contacts().iterator().next();
        GroupData group4Add = app.db().groups().iterator().next();

        app.contact().createWithGroup(before, group4Add);
        ContactData after = app.db().contacts().iterator().next();

        assertThat(before.addGroup(group4Add).getGroups(), equalTo(after.getGroups()));

    }
}
