package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeleteTests extends TestBase {

    @Test
    public void testDeleteGroup() {
        app.getNavHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();

        if (!app.getNavHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("i bought  one mdma", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelGroup();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before - 1);
    }
}
