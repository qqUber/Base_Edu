package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupModTests extends TestBase {
    @Test
    public void testGroupMod() {
        app.getNavHelper().gotoGroupPage();
        if (!app.getNavHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("i bought  one mdma", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupMod();
        app.getGroupHelper().fillGroupForm(new GroupData("3", "4", "333"));
        app.getGroupHelper().updateGroup();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
    }
}
