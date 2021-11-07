package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeleteTests extends TestBase {

    @Test
    public void testDeleteGroup() {
        app.getNavHelper().gotoGroupPage();
        if (!app.getNavHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("i bought  one mdma", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
