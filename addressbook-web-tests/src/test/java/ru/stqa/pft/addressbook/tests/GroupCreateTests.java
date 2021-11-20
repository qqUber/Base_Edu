package ru.stqa.pft.addressbook.tests;

import com.sun.istack.internal.NotNull;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;
import java.util.function.IntSupplier;

public class GroupCreateTests extends TestBase {

    @NotNull
    @Test
    public void testGroupAdd() {
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("OneMDMA");
        app.group().create(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.withId(after.stream()
                .mapToInt(GroupData::getId)
                .max()
                .orElseGet(null));
        before.add(group);
        Assert.assertEquals(before, after);
    }
}