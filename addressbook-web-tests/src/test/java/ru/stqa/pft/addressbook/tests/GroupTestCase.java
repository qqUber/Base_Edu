package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupTestCase extends TestBase {

    @Test
    public void testGroupAdd() {
        app.getNavHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("i bought  one mdma", null, null));

    }
}