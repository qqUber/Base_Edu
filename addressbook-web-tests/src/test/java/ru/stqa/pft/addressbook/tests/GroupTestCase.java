package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupTestCase extends TestBase {

    @Test
    public void testGroupAdd() {
        app.getNavHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("i bought one mdma", null, null));
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
    }
}