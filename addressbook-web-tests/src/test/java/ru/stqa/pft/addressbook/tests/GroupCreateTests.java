package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreateTests extends TestBase {

    @Test
    public void testGroupAdd() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("OneMDMA");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(
                before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream()
                        .mapToInt(GroupData::getId)
                        .max()
                        .orElseGet(null)))));
    }
    @Test
    public void testBadGroupAdd() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("One'");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(
                before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before));
    }
}