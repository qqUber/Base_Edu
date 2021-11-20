package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("213SS"));
        }
    }

    @Test
    public void testGroupMod() {

        Groups before = app.group().all();
        GroupData modGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modGroup.getId())
                .withName("Testmod")
                .withHeader("1100")
                .withFooter("test1MS");
        app.group().modify(group);
        Groups after = app.group().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(
                before.without(modGroup)
                        .withAdded(group)));
    }


}
