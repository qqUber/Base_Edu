package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

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

        Set<GroupData> before = app.group().all();
        GroupData modGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modGroup.getId())
                .withName("Testmod")
                .withHeader("1100")
                .withFooter("test1MS");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }


}
