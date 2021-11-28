package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("213SS"));
        }
    }

    @Test
    public void testGroupMod() {
        Groups before = app.db().groups();
        GroupData modGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modGroup.getId())
                .withName("Testmod")
                .withHeader("1100")
                .withFooter("test1MS");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(
                before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(
                before.without(modGroup)
                        .withAdded(group)));
        verifyGroupListInUI();
    }

}
