package ru.stqa.pft.addressbook.tests;

import com.sun.istack.internal.NotNull;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreateTests extends TestBase {

    @NotNull
    @Test
    public void testGroupAdd() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("OneMDMA");
        app.group().create(group);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream()
                        .mapToInt(GroupData::getId)
                        .max()
                        .orElseGet(null)))));
    }
}