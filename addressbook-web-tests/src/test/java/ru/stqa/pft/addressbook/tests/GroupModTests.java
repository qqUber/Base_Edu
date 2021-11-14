package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModTests extends TestBase {
    @Test
    public void testGroupMod() {
        app.getNavHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        if (!app.getNavHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("i bought  one mdma", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupMod();
        app.getGroupHelper().fillGroupForm(new GroupData("3", "4", "333"));
        app.getGroupHelper().updateGroup();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);
    }
}
