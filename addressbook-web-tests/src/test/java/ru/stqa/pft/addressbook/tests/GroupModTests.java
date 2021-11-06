package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModTests extends TestBase{
    @Test
    public void testGroupMod(){
        app.getNavHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupMod();
        app.getGroupHelper().fillGroupForm(new GroupData("3", "4", "333"));
        app.getGroupHelper().updateGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
